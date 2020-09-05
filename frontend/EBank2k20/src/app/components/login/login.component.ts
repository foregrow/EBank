import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { KorisnikService } from 'src/app/services/korisnik.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  korisnik;
  ngOnInit(){
  
  }
  constructor(private fb: FormBuilder,
    private _korisnikService: KorisnikService,
    private _router: Router){}

  loginForm = this.fb.group({
  korisnickoIme: [''],
  lozinka: ['']
  });

  submitLogin(){
    this._korisnikService.login(this.loginForm.value)
      .subscribe(
        response => {
          localStorage.setItem('jwt',response.jwt)
          localStorage.setItem('role',response.uloga)
          this._korisnikService.getRedirectedFromLogin(response.uloga)
        },
        error => {
          alert('Pogresni podaci!');
        }
      );
  }

  goToOtvaranjeRacuna(){
    this._router.navigate(['zahtev-otvaranja-racuna']);
  }
}
