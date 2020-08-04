import { Injectable } from '@angular/core';
import { CanActivate, Router} from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { FlashMessagesService } from 'angular2-flash-messages';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router,
    private flashMessage: FlashMessagesService
  ) {}

  canActivate(): boolean {
    return true;
  }
}
