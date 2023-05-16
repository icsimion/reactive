import { Injectable } from '@angular/core';

import { User } from './user';

import {Observable} from 'rxjs';

@Injectable()
export class UserReactiveService {

  url: string = 'http://localhost:8080/users-reactive';
  urlPaged: string = 'http://localhost:8080/users-reactive-paged';

  getUserStream(page?: number, size?: number): Observable<User> {
    return new Observable<User>((observer) => {
      let url = this.url;
      if (page != null) {
        url = this.urlPaged + '?page=' + page + '&size=' + size;
      }
      let eventSource = new EventSource(url);
      eventSource.onmessage = (event) => {
        console.debug('Received event: ', event);
        let json = JSON.parse(event.data);
        observer.next(new User(json['id'], json['book'], json['content']));
      };
      eventSource.onerror = (error) => {
        // readyState === 0 (closed) means the remote source closed the connection,
        // so we can safely treat it as a normal situation. Another way
        // of detecting the end of the stream is to insert a special element
        // in the stream of events, which the client can identify as the last one.
        if(eventSource.readyState === 0) {
          console.log('The stream has been closed by the server.');
          eventSource.close();
          observer.complete();
        } else {
          observer.error('EventSource error: ' + error);
        }
      }
    });
  }

}
