import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { User } from '../../models/User';
import { AuthService } from '../../services/auth.service';
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit, OnDestroy {

  isLoading: boolean;
  private authStatusSubs: Subscription;
  user: User = {
    username: '',
    password: '',
    email: '',
    mobile: ''
  };

  constructor(private flashMessage: FlashMessagesService, private authService: AuthService) { }

  ngOnInit(): void {
    this.authStatusSubs = this.authService.getAuthStatusListener()
    .subscribe(authStatus => {
      this.isLoading = false;
    });
  }

  onSubmit({value, valid}: {value: User, valid: boolean}) {
    if(!valid) {
      this.flashMessage.show('Fill out the User form properly!', {
        cssClass: 'alert-danger', timeout: 5000
      });
    }
    this.isLoading = true;
    this.authService.createUser(value);
  }
  ngOnDestroy(): void {
    this.authStatusSubs.unsubscribe();
  }
}
