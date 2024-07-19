import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  private apiUrl = "http://localhost:8080/api/weather";

  constructor(private http: HttpClient) { }

  getWeatherByCoordinates(latitude: number, longitude: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/search?latitude=${latitude}&longitude=${longitude}`);
  }
}
