import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import 'rxjs/add/operator/map';
import {HttpClient} from '@angular/common/http';
import {VersionService} from './version.service';

@Injectable()
export class AuthenticationService {
  private API_URL: string = environment.API_URL;
  private VERSION: string = this._version.getCurrentVersion();

  private token: string;
  private loginFlag: boolean = false;

  constructor(private _http: HttpClient, private _version: VersionService) {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
    if (this.token) {
      this.loginFlag = true;
    }
  }

  login(username: string, password: string) {
    return this.sendCredits(this.VERSION + '/login', username, password);
  }

  register(username: string, password: string) {
    return this.sendCredits(this.VERSION + '/register', username, password);
  }

  logout(): void {
    this.token = null;
    this.loginFlag = false;
    localStorage.removeItem('currentUser');
  }

  sendCredits(path: string, username: string, password: string) {
    const params = {
      'username': username,
      'password': password
    };
    return this._http.get(this.API_URL + path, {params});
  }

  getToken(): string {
    return this.token;
  }

  isLogin(): boolean {
    return this.loginFlag;
  }

  setCredits(username: string, token: string) {
    this.token = token;
    this.loginFlag = true;
    localStorage.setItem('currentUser', JSON.stringify({username: username, token: token}));
  }

}

