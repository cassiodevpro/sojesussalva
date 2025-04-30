import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-contruction',
  templateUrl: './contruction.component.html',
  styleUrls: ['./contruction.component.scss']
})
export class ContructionComponent {

   constructor(
      public router: Router
    ) {}


  irParaHome(): void {
    this.router.navigate(['/']);
  }

}
