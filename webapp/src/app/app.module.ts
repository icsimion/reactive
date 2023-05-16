import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { UserReactiveService } from './user-reactive.service';
import { UserBlockingService } from './user-blocking.service';
import { AppComponent } from './app.component';
import { UsersComponent } from './users.component';
import { UserDetailComponent } from './user-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    UserDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    UserReactiveService,
    UserBlockingService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
