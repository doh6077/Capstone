import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Trashbins} from '../trashbins';
import { TrashbinsService } from '../trashbins.service';
import { ActivatedRoute, RouterLink } from '@angular/router';

@Component({
  selector: 'app-Trashbins-add',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './Trashbins-add.component.html',
  styleUrl: './Trashbins-add.component.css'
})
export class TrashbinsAddComponent {

  trashbins: Trashbins ={
    binId:0,
    name:'',
    height:0,
    createdDate:''
  }
  //Connect to connect the Trashbins Service component
  constructor(private trashbinsService:TrashbinsService){}
  //Method called by the HTML button
  saveTrashbins():void{
    //Read in the fields from the inputs
    const data={
      name:this.trashbins.name,
      height:this.trashbins.height,
      createdDate:this.trashbins.createdDate
    };
    //Submit the Trashbins record to the Rest Controller
    this.trashbinsService.create(data).subscribe(
      (data:Trashbins)=>this.trashbinsService.onTrashbinsAdded.emit(data)
    );
  }
}