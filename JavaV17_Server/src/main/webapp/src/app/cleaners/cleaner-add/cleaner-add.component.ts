import { Component } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Cleaner } from '../cleaner';
import { CleanerService } from '../cleaner.service';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink, Router } from '@angular/router';
import { ShiftService } from '../../shifts/shift.service';
import { Shift } from '../../shifts/shift';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-cleaner-add',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    MatFormFieldModule,
    CommonModule,
    MatSelectModule,
    ReactiveFormsModule
  ],
  templateUrl: './cleaner-add.component.html',
  styleUrl: './cleaner-add.component.css'
})
export class CleanerAddComponent {

  // List of all available shifts fetched from the backend
  shiftList: Shift[] = [];

  // Reactive form control to track selected shift IDs
  shiftsSelected = new FormControl<number[] | null>([]);

  // Cleaner object bound to form input fields
  cleaner: Cleaner = {
    id: 0,
    name: '',
    email: '',
    phoneNumber: '',
    shiftIds: []
  };

  // Inject CleanerService and ShiftService for API calls, and Router for navigation
  constructor(
    private cleanerService: CleanerService,
    private shiftService: ShiftService,
    private router: Router
  ) {}

  // Lifecycle hook to load shift options when component is initialized
  ngOnInit(): void {
    // Fetch all available shifts from the ShiftService
    this.shiftService.getAll().subscribe({
      next: (data) => {
        this.shiftList = data; // Populate the dropdown list with shift data
      },
      error: (err) => {
        // Handle API error when loading shifts
      }
    });

    // Optional: Listen to selection changes (useful for debugging or form validation)
    this.shiftsSelected.valueChanges.subscribe(value => {
      // Can log or respond to shift selection changes here
    });
  }

  // Called when user clicks "Save" to submit the new cleaner
  saveCleaner(): void {
    // Build a request payload using input field values and selected shift IDs
    const data = {
      name: this.cleaner.name,
      email: this.cleaner.email,
      phoneNumber: this.cleaner.phoneNumber,
      shiftIds: this.shiftsSelected.value ?? [] // fallback to empty array
    };

    // Send POST request to create new cleaner with assigned shifts
    this.cleanerService.create(data).subscribe({
      next: (response: Cleaner) => {
        // Emit event for other components (e.g., list refresh), show alert, and redirect
        this.cleanerService.onCleanerAdded.emit(response);
        alert("Cleaner saved successfully!");
        this.router.navigate(['/cleaners']);
      },
      error: (error) => {
        // Handle and show error if the API request fails
        alert("Error saving cleaner: " + error.message);
      }
    });
  }
}
