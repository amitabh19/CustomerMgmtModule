import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError, observable } from 'rxjs';
import {catchError} from 'rxjs/operators';
import { ProductFeedback } from './ProductFeedback';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = 'http://localhost:8081/customer';
  constructor(private http: HttpClient) { }

  create(ProductFeedback: Object) {
    return this.http.post<boolean>(this.baseUrl+"/addFeedback",ProductFeedback);
  }
  
  getFeedbackByProductId(productId: number) {
		return this.http.get<ProductFeedback>(this.baseUrl+"/getProductFeedback/"+productId);
	}
}
