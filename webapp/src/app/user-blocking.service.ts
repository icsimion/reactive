import { Injectable } from '@angular/core';

import { User } from './user';

import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class UserBlockingService {

  url: string = 'http://localhost:8080/users-blocking';
  urlPaged: string = 'http://localhost:8080/users-blocking-paged';

  constructor(private http: HttpClient) {}

  getUsers(page?: number, size?: number): Observable<Array<User>> {
    let url = this.url;
    if (page != null) {
      url = this.urlPaged + '?page=' + page + '&size=' + size;
    }
    return this.http.get<Array<User>>(url);
  }

}
