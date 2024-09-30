/**
 * @author jose.inestroza@unah.edu.hn
 * @version 0.1.0
 * @date 02/05/2023
 * @description Esta clase por medio de comunicación asíncrona llamará un service que controlará la limpieza del modelo de datos
 */

 class ActionClean{
	 
 	constructor(mostrarReg, totalReg, totalComments, totalShares, totalLikes, totalVisual){
		 this.mostrarReg = mostrarReg;
		 this.totalReg = totalReg;
		 this.totalComments = totalComments;
		 this.totalShares = totalShares;
		 this.totalLikes = totalLikes;
		 this.totalVisual = totalVisual;
	 }
	 
	 /**
	  * @co-author fgutierrezc@unah.hn
	  * @description Se modificó este método para que limpiara el modelo de datos y cree los archivos.log
	  */
	 
	 processRequest(event){
		let xhr = event.target;
		
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				try{
					let response = JSON.parse(xhr.responseText);
					if(response.status){
						this.totalReg.innerHTML = response.TotalTwitts;
						this.totalReg.value = response.TotalTwitts;
						this.totalComments.innerHTML = response.TotalComments;
						this.totalShares.innerHTML = response.TotalShares;
						this.totalLikes.innerHTML = response.TotalLikes;
						this.totalVisual.innerHTML = response.TotalVisual;
						
						this.mostrarReg.innerHTML = "<div class=\"text-center p-2 m-2 bg-light\"><h3 class=\"text-center\">Listado de registros actuales en el modelo de datos</h3></div>";
						
					}else{
						
					}
				}catch(ex){
					
				}
			}else{
				
			}
		}else{
			
		}
	}
	
	send(event){
		
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "modules/controllers/serviceClean.jsp", true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = this.processRequest.bind(this);
				
		xhr.send();
		
	}
	
}	