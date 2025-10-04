import { Component } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ShiftService } from '../shift.service';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink, Router } from '@angular/router';
import { Shift } from '../../shifts/shift';

@Component({
  selector: 'app-shift-add',
  imports: [FormsModule, RouterLink,  CommonModule, ReactiveFormsModule],
  templateUrl: './shift-add.component.html',
  styleUrl: './shift-add.component.css'
})
export class ShiftAddComponent {

  shift: Shift ={
    id:0,
    dayOfWeek: "",
    shiftTime: ""
  }

  dayOfWeek = [];


  constructor( private shiftService:ShiftService, private router: Router ){}


  ngOnInit(): void {
        this.shiftService.getAllDayOfWeek().subscribe({
          next: (data) => {
            console.log('shifts loaded:', data);
            this.dayOfWeek = data;
          },
          error: (err) => {
            //console.error('Failed to load shifts:', err);
          }
        });
        // this.shiftsSelected.valueChanges.subscribe(value => {
        //   //console.log('Selected shift IDs changed:', value);
        // });
      }
  //Method called by the HTML button
  saveShift():void{
    //Read in the fields from the inputs
    const data={
      dayOfWeek:this.shift.dayOfWeek,
      shiftTime:this.shift.shiftTime,

    };
    //Submit the shift record to the Rest Controller
    this.shiftService.create(data).subscribe({
      next: (response: Shift) => {
        //console.log("SUCCESS! Response:", response);
        this.shiftService.onShiftAdded.emit(response);
        alert("Shift saved successfully!");
        this.router.navigate(['/shifts']);
      },
      error: (error) => {
        //console.error("ERROR:", error);
        alert("Error saving shift: " + error.message);
      }
    });
  }
}
