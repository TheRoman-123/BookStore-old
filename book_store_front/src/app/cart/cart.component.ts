import { Component, OnInit } from '@angular/core';
import { CartService } from './cart.service';
import {BookWithSaleAmount} from "../types/BookWithSaleAmount";
import {SaleService} from "../services/sale.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  booksWithSaleAmount: Array<BookWithSaleAmount> = [];

  constructor(private cartService: CartService,
              private saleService: SaleService) {}

  ngOnInit(): void {
    this.booksWithSaleAmount = this.cartService.getBooks();
  }


  createSale() {
    let cost: number =
      this.booksWithSaleAmount.map(a => a.book.price)
        .reduce((sum, price) => sum + price, 0);

    this.saleService.createSale(cost, 1, this.booksWithSaleAmount).subscribe(
      (data) => {
        alert("Your order successfully saved! Order ID:" + (data as number));
      }
    );
  }
}
