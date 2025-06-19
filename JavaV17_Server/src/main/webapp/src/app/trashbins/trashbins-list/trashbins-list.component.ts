import { Component, Input, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Trashbins } from '../trashbins';
import { TrashbinsService } from '../trashbins.service';

@Component({
  selector: 'app-trashbins-list',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './trashbins-list.component.html',
  styleUrl: './trashbins-list.component.css'
})
export class TrashbinsListComponent implements OnInit {
  trashbins: Trashbins[] = [];
  @Input() id = 0;

  constructor(private trashbinsService: TrashbinsService) {}

  ngOnInit(): void {
    this.getTrashbins();
    this.trashbinsService.onTrashbinsAdded.subscribe(
      (data: Trashbins) => this.trashbins.push(data)
    );
  }

  getTrashbins(): void {
    this.trashbinsService.getAll().subscribe({
      next: (data) => {
        this.trashbins = data;
      },
      error: (err) => {
        console.error('Failed to load trashbin history:', err);
      }
    });
  }
}
