import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, retry, throwError} from "rxjs";
import {Location} from "../../models/Location";

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private http:HttpClient) { }

  getLocationById(id: number): Observable<Location> {
    return this.http.get<Location>('http://localhost:8080/api/location/' + id).pipe(
      retry(3),
      catchError(this.handleError)
    );
  }

  getAllLocations(): Observable<Location[]> {
    return this.http.get<Location[]>('http://localhost:8080/api/location/all').pipe(
      retry(3),
      catchError(this.handleError)
    )
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
