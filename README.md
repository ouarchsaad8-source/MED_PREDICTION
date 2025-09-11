# MedPrediction - AI Medical Diagnosis Assistant

A comprehensive Angular frontend application for medical prediction and diagnosis assistance using AI technology.

## Features

### Authentication
- **Login/Register Pages**: Secure authentication flow with form validation
- **Route Protection**: Dashboard access requires authentication
- **User Session Management**: LocalStorage-based session handling

### Dashboard & Navigation
- **Responsive Layout**: Professional sidebar navigation with collapsible menu
- **Top Navigation**: User profile display with logout functionality
- **Statistics Cards**: Monthly predictions, confirmed diagnoses, saved reports
- **Recent Predictions**: Quick access to latest prediction results

### Medical Prediction System
- **Symptom Selection**: Multi-category symptom picker with tag-based interface
- **Case Description**: Detailed symptom description with validation
- **Severity Slider**: 1-10 scale for symptom severity assessment
- **AI Analysis**: Simulated AI prediction with loading states
- **Results Display**: Ranked disease predictions with probability scores

### Prediction Results
- **Disease Rankings**: Probability-based condition listings
- **Medication Recommendations**: Detailed treatment suggestions with dosages
- **Expandable Details**: Collapsible sections for detailed information
- **Action Buttons**: Save, download PDF, view full details

### Profile Management
- **User Information**: Display personal and professional details
- **Edit Modal**: Form-based profile editing with validation
- **Settings Toggle**: Email notifications, SMS alerts, data sharing preferences

### Prediction History
- **Detailed View**: Complete prediction history with timestamps
- **Status Tracking**: Timeline view of prediction status
- **Clinical Notes**: Additional medical notes and observations
- **Export Options**: Download reports and share functionality

### Help & Support
- **Quick Start Guide**: Step-by-step usage instructions
- **FAQ Section**: Expandable frequently asked questions
- **Contact Support**: Multiple contact methods with icons
- **Medical Disclaimers**: Important safety and legal information

## Technical Stack

- **Framework**: Angular 16+
- **Styling**: SCSS with CSS custom properties
- **Forms**: Reactive Forms with validation
- **Routing**: Angular Router with nested routes
- **Animations**: CSS transitions and keyframe animations
- **Icons**: Inline SVG icons
- **Responsive**: Mobile-first responsive design

## Project Structure

```
src/
├── app/
│   ├── auth/                    # Authentication components
│   ├── features/                # Feature modules
│   │   ├── dashboard/           # Main dashboard
│   │   ├── profile/             # User profile management
│   │   ├── prediction/          # Prediction form
│   │   ├── prediction-details/  # Detailed prediction view
│   │   └── help/                # Help and support
│   ├── layout/                  # Layout components
│   │   ├── dashboard-layout/    # Main layout wrapper
│   │   ├── navbar/              # Top navigation
│   │   └── sidebar/             # Side navigation
│   ├── shared/                  # Shared components
│   │   └── components/          # Reusable UI components
│   ├── app-routing.module.ts    # Route configuration
│   └── app.module.ts            # Main app module
├── assets/
│   ├── data/                    # Mock JSON data
│   └── images/                  # Static images
└── styles.scss                  # Global styles and theme
```

## Design System

### Color Palette
- **Primary Blue**: #1E6FD8
- **Background Light**: #F4F6F8
- **Text Primary**: #2C3E50
- **Success**: #27AE60
- **Warning**: #F39C12
- **Danger**: #E74C3C

### Typography
- **Font Family**: Inter, system fonts
- **Headings**: 600 weight, various sizes
- **Body Text**: 400 weight, 1.6 line height

### Components
- **Cards**: Rounded corners, subtle shadows
- **Buttons**: Multiple variants (primary, secondary, outline)
- **Forms**: Consistent styling with validation states
- **Modals**: Backdrop blur with slide-in animation

## Getting Started

1. **Install Dependencies**
   ```bash
   npm install
   ```

2. **Start Development Server**
   ```bash
   npm start
   ```

3. **Build for Production**
   ```bash
   npm run build
   ```

## Usage

1. **Authentication**: Start at `/login` or `/register`
2. **Dashboard**: View statistics and recent predictions
3. **New Prediction**: Use symptom selector and form
4. **View Results**: Analyze AI predictions and recommendations
5. **Profile**: Manage personal information and settings
6. **Help**: Access documentation and support

## Mock Data

The application uses static JSON files for demonstration:
- `assets/data/mock-predictions.json`: Sample prediction results
- `assets/data/user-data.json`: User profiles and symptom database

## Responsive Design

- **Desktop**: Full sidebar and multi-column layouts
- **Tablet**: Collapsible sidebar with adapted layouts
- **Mobile**: Stack layouts with touch-friendly interactions

## Medical Disclaimer

This application is for demonstration purposes only. All medical predictions are simulated and should not be used for actual medical diagnosis or treatment decisions.

## Browser Support

- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+
