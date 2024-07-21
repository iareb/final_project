import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {catchError, map, Observable, retry, throwError} from "rxjs";
import {WeatherDto} from "../../models/WeatherDto";

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  private apiUrl = "http://localhost:8080/api/weather";

  constructor(private http: HttpClient) { }

  fetchWeatherDataByLocationName(name: string): Observable<WeatherDto> {
    const params = new HttpParams().set("name", name);
    return this.http.get<WeatherDto>(this.apiUrl + '/location', {params})
      .pipe(
        map(data => {
          const hourlyTime = JSON.parse(data.hourlyTime);
          const hourlyTemperature = JSON.parse(data.hourlyTemperature);
          const hourlyHumidity = JSON.parse(data.hourlyHumidity);
          const hourlyWindSpeed = JSON.parse(data.hourlyWindSpeed);

          const hourlyWeather = hourlyTime.map((time: string | number | Date, index: string | number) => ({
            time: new Date(time),
            temperature: hourlyTemperature[index],
            humidity: hourlyHumidity[index],
            windSpeed: hourlyWindSpeed[index],
          }));

          console.log('Processed weather data:', {
            ...data,
            hourlyWeather
          });

          return {
            ...data, hourlyWeather
          };
        }),
        retry(3),
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }

}
