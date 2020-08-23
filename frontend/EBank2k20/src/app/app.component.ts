import { Component } from '@angular/core';
import { KorisnikService } from './services/korisnik.service';
import { RouterLink, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(public korisnikService: KorisnikService,
    public router: ActivatedRoute){

  }
}
