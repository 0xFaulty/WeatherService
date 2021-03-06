import {Component, OnInit} from '@angular/core';
import {WeatherService} from '../../shared/weather.service';
import {ErrorInfo, HistoryInfo, InfoResponse} from '../../entity';
import {AuthenticationService} from '../../shared/authentication.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss']
})
export class HistoryComponent implements OnInit {

  searching: boolean = false;

  errorFlag: boolean = false;
  errorMessage: string = '';

  queuePos: string = '';

  list: any[] = [];

  resultFound: boolean = false;

  constructor(private _weatherService: WeatherService, private _auth: AuthenticationService) {
  }

  ngOnInit() {
    this._weatherService.currentMessage.subscribe(() => this.getSomething());
  }

  getSomething() {
    this.searching = true;
    return this._weatherService.getByHistory().subscribe(
      data => this.handleSuccess(data),
      error => console.log(error),
      () => this.searching = false
    );
  }

  handleSuccess(data) {
    let response = <InfoResponse>data;
    if (response.type === 'ERROR') {
      let er = <ErrorInfo>response.info;
      this.errorFlag = true;
      this.errorMessage = er.message;
    }
    if (response.type === 'HISTORY') {
      let qr = <HistoryInfo>response.info;
      this.list = qr.history;
      this.resultFound = true;
    }
  }

  isLogin(): boolean {
    return this._auth.isLogin();
  }

}
