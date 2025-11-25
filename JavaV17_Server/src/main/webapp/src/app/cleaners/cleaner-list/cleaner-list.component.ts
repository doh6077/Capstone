import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Cleaner } from '../cleaner';
import { CleanerService } from '../cleaner.service';
import { Shift } from '../../shifts/shift';
import { ShiftService } from '../../shifts/shift.service';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { NgIf } from '@angular/common';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-cleaner-list',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    MatFormFieldModule,
    CommonModule,
    MatSelectModule,
    ReactiveFormsModule,
    NgIf,
  ],
  templateUrl: './cleaner-list.component.html',
  styleUrl: './cleaner-list.component.css',
})
export class CleanerListComponent implements OnInit {
  cleaners: Cleaner[] = [];
  shiftList: Shift[] = [];

  cleaner: Cleaner = {
    id: 0,
    name: '',
    email: '',
    phoneNumber: '',
    shiftIds: [],
    isEdit: false,
  };

  constructor(
    private cleanerService: CleanerService,
    private shiftService: ShiftService
  ) {}

  ngOnInit(): void {
    this.getCleaners();
    this.cleanerService.onCleanerAdded.subscribe((data: Cleaner) =>
      this.cleaners.push(data)
    );
    this.shiftService.getAll().subscribe({
      next: (data) => {
        //console.log('shifts loaded:', data);
        this.shiftList = data;
      },
      error: (err) => {
        //console.error('Failed to load shifts:', err);
      },
    });
  }

  getCleaners(): void {
    this.cleanerService.getAll().subscribe({
      next: (data) => {
        this.cleaners = data.map((c) => ({ ...c, isEdit: false }));
      },
      error: (err) => {
        console.error('Failed to load cleaner history:', err);
      },
    });
  }
  deleteCleaner(id: number): void {
    if (confirm('Are you sure you want to delete ' + id + '?')) {
      this.cleanerService.delete(id).subscribe(() => {
        this.cleaners = this.cleaners.filter((p) => p.id !== id);
      });
    }
  }

onEdit(cleaner: Cleaner) {
  // Make sure only one cleaner row is in "edit" mode at a time
  this.cleaners.forEach((c) => {
    if (c !== cleaner) c.isEdit = false;
  });

  // Enable edit mode for the selected cleaner
  cleaner.isEdit = true;

  // If shiftIds are not initialized, build them from the existing shifts relation
  // This preserves previously selected shifts when entering edit mode
  if (!cleaner.shiftIds || cleaner.shiftIds.length === 0) {
    cleaner.shiftIds = cleaner.shifts?.map((s) => s.id!) ?? [];
  }

  // Keep a separate copy of the cleaner for the edit form
  // so changes in the form don't immediately mutate the list item
  this.cleaner = {
    ...cleaner,
    // Clone shiftIds to avoid mutating the original array by reference
    shiftIds: [...(cleaner.shiftIds ?? [])],
  };
}


onCancel(cleaner: Cleaner) {
  // Exit edit mode for this cleaner row
  cleaner.isEdit = false;

  // Restore the original values from the backup `this.cleaner`
  cleaner.name = this.cleaner.name;
  cleaner.email = this.cleaner.email;
  cleaner.phoneNumber = this.cleaner.phoneNumber;

  // Restore previously selected shifts
  cleaner.shiftIds = this.cleaner.shiftIds;
}

onUpdate(updatedCleaner: Cleaner): void {
  // Prepare the payload to send to the backend (only fields that can be updated)
  const data = {
    name: updatedCleaner.name,
    email: updatedCleaner.email,
    phoneNumber: updatedCleaner.phoneNumber,
    // Ensure shiftIds is always an array when sending to the API
    shiftIds: updatedCleaner.shiftIds ?? [],
  };

  // Ask the user for confirmation before updating this cleaner
  if (confirm('Are you sure you want to edit ' + updatedCleaner.id + '?')) {
    // Call the service to update the cleaner on the server
    this.cleanerService.update(updatedCleaner.id!, data).subscribe({
      next: () => {
        // Notify the user and refresh the list after a successful update
        alert('Cleaner Updated');
        this.getCleaners();
      },
      error: (err) => {
        // Log any errors that occur during the update
        console.error('Failed to update cleaner:', err);
      },
    });
  }
}
}
