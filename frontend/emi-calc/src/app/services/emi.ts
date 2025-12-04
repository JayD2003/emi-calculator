import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmiService {

  private baseUrl = 'http://localhost:8080/api/emi';

  constructor(private http: HttpClient) {}

  calculateEmi(data:any): Observable<any>{
    return this.http.post(`${this.baseUrl}/calculate`, data);
  }
}
