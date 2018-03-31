import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../shared/authentication.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  constructor(private _auth: AuthenticationService) {
  }

  ngOnInit() {
  }

  isLogin(): boolean {
    return this._auth.isLogin();
  }

}
