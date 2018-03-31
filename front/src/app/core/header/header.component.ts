import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../shared/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private _router: Router, private _auth: AuthenticationService) {
  }

  ngOnInit() {
  }

  logout() {
    this._auth.logout();
    this._router.navigate(['login']);
  }

  isLogin() {
    return this._auth.isLogin();
  }

}
