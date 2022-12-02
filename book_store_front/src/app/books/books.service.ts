import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {HttpOptions} from "../types/Http";
import {map, Observable, of, tap, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {Book} from "../types/Book";

@Injectable()
export class BooksService {
  serverUrl: string = "http://localhost:8080/warehouses";
  httpOptions: HttpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {}

  getBooks(): Observable<Book[]> {
    return this.http.get(this.serverUrl).pipe(
      map((data) => {
        let list: any[] = data as any[];

        return list.map((book) => {
          return {
            id: book.id,
            price: book.price,
            title: book.title,
            image: book.imagePath,
            authors: book.authors
          }
        });
      }),
      catchError((err) => {
        alert('Попробуйте связаться с сервером позже.' + err);
        return throwError(err);
      })
    );
  }

    /*return [
      {
        name: 'clean code',
        author: 'robert c martin',
        image:
          'https://images-na.ssl-images-amazon.com/images/I/41zoxjP9lcL._SX323_BO1,204,203,200_.jpg',
        amount: 700,
      },
      {
        name: 'pragmatic programmer',
        author: 'david thomas',
        image: 'https://m.media-amazon.com/images/I/518FqJvR9aL.jpg',
        amount: 800,
      },
      {
        name: 'Art of Computer Programming',
        author: 'Donald John Fuller',
        image:
          'https://images-na.ssl-images-amazon.com/images/I/41gCSRxxVeL._SY429_BO1,204,203,200_.jpg',
        amount: 18300,
      },
      {
        name: 'Introduction to Algorithms',
        author: 'T Cormen',
        image:
          'https://images-na.ssl-images-amazon.com/images/I/41VndKVtiXL._SX442_BO1,204,203,200_.jpg',
        amount: 1500,
      },
    ];*/

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      // console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.id}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    }
  }

}
