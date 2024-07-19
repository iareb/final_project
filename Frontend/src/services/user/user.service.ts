import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {UserRegistrationDto} from "../../models/UserRegistrationDto";
import {catchError, Observable, retry, throwError} from "rxjs";
import {UserLoginDto} from "../../models/UserLoginDto";
import {UserDto} from "../../models/UserDto";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public isLogged: boolean = false;
  public userEmail: string;

  constructor(private http:HttpClient) { }

  userRegistration(newUser: UserRegistrationDto): Observable<UserRegistrationDto> {
    const header = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<UserRegistrationDto>('http://localhost:8080/api/user/registration', JSON.stringify(newUser),
      {headers: header}).pipe(
      retry(3),
      catchError(this.handleError)
    )
  }

  login(user: UserLoginDto): Observable<UserLoginDto> {
    const header = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<UserLoginDto>('http://localhost:8080/api/user/login', JSON.stringify(user), {headers: header})
      .pipe(
        retry(3),
        catchError(this.handleError)
      )
  }

  getUserById(id: number): Observable<UserDto> {
    return this.http.get<UserDto>('http://localhost:8080/api/user/'+id).pipe(
      retry(3),
      catchError(this.handleError)
    )
  }

  getUserByEmail(email: string): Observable<UserDto> {
    return this.http.get<UserDto>('http://localhost:8080/api/user/search',
      {params: {email: email} }).pipe(
      catchError(this.handleError)
    )
  }

  getAll():Observable<UserDto[]> {
    return this.http.get<UserDto[]>('http://localhost:8080/api/user/all').pipe(
      catchError(this.handleError)
    )
  }

  updateUser(user: UserDto):Observable<UserDto> {
    const header = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.put<UserDto>('http://localhost:8080/api/user/update', JSON.stringify(user), {headers: header})
      .pipe(
        retry(3),
        catchError(this.handleError)
      )
  }

  deleteUser(email: string): Observable<UserDto> {
    return this.http.delete<UserDto>('http://localhost:8080/api/user/delete/' + email).pipe(
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
