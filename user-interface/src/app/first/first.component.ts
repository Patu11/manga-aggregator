import { Component, OnInit } from '@angular/core';
import { TestService } from '../service/test.service';

@Component({
  selector: 'app-first',
  templateUrl: './first.component.html',
  styleUrls: ['./first.component.css']
})
export class FirstComponent implements OnInit {

  constructor(private testService: TestService) { }

  ngOnInit(): void {
    this.testService.test().subscribe(
      data => {
        console.log(data);
      }
    );
  }

}
