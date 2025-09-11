import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

interface MenuItem {
  label: string;
  icon: string;
  route: string;
  active?: boolean;
}

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {
  @Input() isCollapsed = false;

  menuItems: MenuItem[] = [
    {
      label: 'Dashboard',
      icon: 'dashboard',
      route: '/dashboard'
    },
    {
      label: 'Profile',
      icon: 'user',
      route: '/dashboard/profile'
    },
    {
      label: 'Prediction',
      icon: 'brain',
      route: '/dashboard/prediction'
    },
    {
      label: 'Prediction Details',
      icon: 'file-text',
      route: '/dashboard/prediction-details/1'
    },
    {
      label: 'Help',
      icon: 'help-circle',
      route: '/dashboard/help'
    }
  ];

  constructor(private router: Router) {}

  navigateTo(route: string) {
    this.router.navigate([route]);
  }

  isActiveRoute(route: string): boolean {
    return this.router.url === route || 
           (route === '/dashboard' && this.router.url === '/dashboard');
  }
}
