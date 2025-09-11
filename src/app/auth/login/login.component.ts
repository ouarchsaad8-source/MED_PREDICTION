import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup;
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.isLoading = true;
      
      // Simulate API call
      setTimeout(() => {
        this.isLoading = false;
        // Store user data in localStorage for demo
        localStorage.setItem('currentUser', JSON.stringify({
          id: 1,
          name: 'Dr. Sarah Johnson',
          email: this.loginForm.value.email,
          avatar: 'assets/images/avatar.jpg'
        }));
        this.router.navigate(['/dashboard']);
      }, 1500);
    }
  }

  navigateToRegister() {
    this.router.navigate(['/register']);
  }
}
