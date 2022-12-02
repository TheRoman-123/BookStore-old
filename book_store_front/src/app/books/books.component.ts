import { Component, OnInit } from '@angular/core';
import { Book } from '../types/Book';
import { BooksService } from './books.service';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css'],
})
export class BooksComponent implements OnInit {
  books: Book[] = [];
  constructor(private booksService: BooksService) {}

  isShowing: boolean = true;

  ngOnInit(): void {
    this.booksService.getBooks().subscribe((data) => {
      this.books = data;
    });

    /*this.booksService.getBookImages().subscribe((data) => {

    });*/
  }
}
