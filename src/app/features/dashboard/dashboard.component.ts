import { Component, OnInit } from '@angular/core';

interface StatCard {
  title: string;
  value: string;
  icon: string;
  color: string;
}

interface RecentPrediction {
  id: number;
  date: string;
  result: string;
  status: string;
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  currentUser: any;
  
  statCards: StatCard[] = [
    {
      title: 'Predictions this month',
      value: '24',
      icon: 'activity',
      color: 'primary'
    },
    {
      title: 'Confirmed diagnoses',
      value: '18',
      icon: 'check-circle',
      color: 'success'
    },
    {
      title: 'Saved reports',
      value: '12',
      icon: 'file-text',
      color: 'warning'
    }
  ];

  recentPredictions: RecentPrediction[] = [
    {
      id: 1,
      date: '2024-01-15',
      result: 'Possible respiratory infection',
      status: 'completed'
    },
    {
      id: 2,
      date: '2024-01-14',
      result: 'Cardiovascular assessment needed',
      status: 'pending'
    },
    {
      id: 3,
      date: '2024-01-13',
      result: 'Allergic reaction suspected',
      status: 'completed'
    }
  ];

  ngOnInit() {
    const userData = localStorage.getItem('currentUser');
    if (userData) {
      this.currentUser = JSON.parse(userData);
    }
  }
}
