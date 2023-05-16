import {User} from './user';
import {UserReactiveService} from './user-reactive.service';
import {UserBlockingService} from './user-blocking.service';

import {Observable} from 'rxjs';
import {ChangeDetectorRef, Component} from "@angular/core";

@Component({
  selector: 'app-component-users',
  providers: [UserReactiveService],
  templateUrl: './users.component.html'
})
export class UsersComponent {

  userArray: User[] = [];
  selectedUser: User | undefined;
  mode: string;
  pagination: boolean;
  page: number;
  size: number;

  constructor(private userReactiveService: UserReactiveService, private userBlockingService: UserBlockingService, private cdr: ChangeDetectorRef) {
    this.mode = "reactive";
    this.pagination = true;
    this.page = 0;
    this.size = 50;
  }

  resetData() {
    this.userArray = [];
  }

  requestUserStream(): void {
    this.resetData();
    let userObservable: Observable<User>;
    if (this.pagination === true) {
      userObservable = this.userReactiveService.getUserStream(this.page, this.size);
    } else {
      userObservable = this.userReactiveService.getUserStream();
    }
    userObservable.subscribe(user => {
      this.userArray.push(user);
      this.cdr.detectChanges();
    });
  }

  requestUserBlocking(): void {
    this.resetData();
    if (this.pagination === true) {
      this.userBlockingService.getUsers(this.page, this.size)
        .subscribe(q => this.userArray = q);
    } else {
      this.userBlockingService.getUsers()
        .subscribe(q => this.userArray = q);
    }
  }

  onSelect(user: User): void {
    this.selectedUser = user;
    this.cdr.detectChanges();
  }
}
