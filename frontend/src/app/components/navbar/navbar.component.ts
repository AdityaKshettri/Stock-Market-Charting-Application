import { Component, OnInit } from '@angular/core';

import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isAuthenticated: boolean = true;
  isAdmin: boolean = true;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    //this.isAuthenticated = this.authService.getIsAuth();
    //this.isAdmin = this.authService.isAdmin();
  }

  onLogoutClick() {
    this.isAuthenticated = false;
  }

}
