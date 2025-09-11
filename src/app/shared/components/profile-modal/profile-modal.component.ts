import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

interface UserProfile {
  name: string;
  email: string;
  age: number;
  gender: string;
  contact: string;
  specialization?: string;
  experience?: string;
}

@Component({
  selector: 'app-profile-modal',
  templateUrl: './profile-modal.component.html',
  styleUrls: ['./profile-modal.component.scss']
})
export class ProfileModalComponent implements OnInit {
  @Input() userProfile!: UserProfile;
  @Output() close = new EventEmitter<void>();
  @Output() profileUpdated = new EventEmitter<UserProfile>();

  profileForm!: FormGroup;
  isLoading = false;

  genderOptions = ['Male', 'Female', 'Other', 'Prefer not to say'];
  specializationOptions = [
    'Internal Medicine',
    'Cardiology',
    'Neurology',
    'Pediatrics',
    'Dermatology',
    'Psychiatry',
    'Emergency Medicine',
    'Family Medicine',
    'Other'
  ];

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.initializeForm();
  }

  initializeForm() {
    this.profileForm = this.fb.group({
      name: [this.userProfile.name, [Validators.required, Validators.minLength(2)]],
      email: [this.userProfile.email, [Validators.required, Validators.email]],
      age: [this.userProfile.age, [Validators.required, Validators.min(18), Validators.max(100)]],
      gender: [this.userProfile.gender, [Validators.required]],
      contact: [this.userProfile.contact, [Validators.required]],
      specialization: [this.userProfile.specialization || ''],
      experience: [this.userProfile.experience || '']
    });
  }

  onSubmit() {
    if (this.profileForm.valid) {
      this.isLoading = true;
      
      // Simulate API call
      setTimeout(() => {
        this.isLoading = false;
        this.profileUpdated.emit(this.profileForm.value);
      }, 1000);
    }
  }

  onClose() {
    this.close.emit();
  }

  onBackdropClick(event: Event) {
    if (event.target === event.currentTarget) {
      this.onClose();
    }
  }
}
