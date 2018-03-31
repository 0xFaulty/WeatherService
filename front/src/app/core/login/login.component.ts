import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../shared/authentication.service';
import {Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';
import {Token} from '../../entity/token';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  showSpinner: boolean = false;

  username: string = 'guest';
  password: string = 'guest';

  errorMessage = '';
  loading: boolean = false;

  constructor(private _auth: AuthenticationService, private _router: Router) {
  }

  ngOnInit() {
  }

  login() {
    this._auth.login(this.username, this.password)
      .subscribe(
        data => {
          let token = (<Token>data).token;
          this._auth.setCredits(this.username, token);
          this._router.navigate(['']);
        },
        error => {
          alert(error.error);
        }
      );
  }

  register() {
    this._auth.register(this.username, this.password)
      .subscribe(
        data => {
          let token = (<Token>data).token;
          this._auth.setCredits(this.username, token);
          this._router.navigate(['']);
        },
        error => {
          alert(error.error);
        }
      );
  }

  isLogin(): boolean {
    return this._auth.isLogin();
  }

}
