import {Component, OnInit} from '@angular/core';
import {BackTaskService} from '../../shared/backtask.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  searchQuery: string = '';
  searching: boolean = false;

  list: any[] = [];

  resultFound: boolean = false;

  constructor(private _weatherService: BackTaskService) {
  }

  ngOnInit() {
    this._weatherService.currentMessage.subscribe(message => this.getSomething(message));
  }

  getSomething(query: string) {
    this.searching = true;
    return this._weatherService.getByCityName(query).subscribe(
      data => this.handleSuccess(data),
      error => console.log(error),
      () => this.searching = false
    );
  }

  handleSuccess(data) {
    this.list.unshift(data);
    this.resultFound = true;
  }

}
