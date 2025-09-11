import { Component, OnInit } from '@angular/core';

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
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  userProfile: UserProfile = {
    name: 'Dr. Sarah Johnson',
    email: 'sarah.johnson@medprediction.com',
    age: 34,
    gender: 'Female',
    contact: '+1 (555) 123-4567',
    specialization: 'Internal Medicine',
    experience: '8 years'
  };

  showEditModal = false;

  ngOnInit() {
    // Load user profile from localStorage or API
    const userData = localStorage.getItem('currentUser');
    if (userData) {
      const user = JSON.parse(userData);
      this.userProfile.name = user.name;
      this.userProfile.email = user.email;
    }
  }

  openEditModal() {
    this.showEditModal = true;
  }

  closeEditModal() {
    this.showEditModal = false;
  }

  onProfileUpdated(updatedProfile: UserProfile) {
    this.userProfile = { ...updatedProfile };
    this.closeEditModal();
    
    // Update localStorage
    const currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    currentUser.name = updatedProfile.name;
    currentUser.email = updatedProfile.email;
    localStorage.setItem('currentUser', JSON.stringify(currentUser));
  }
}
