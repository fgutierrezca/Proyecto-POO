/**
 * @author jose.inestroza@unah.edu.hn
 * @version 0.1.0
 * @date 02/05/2023
 * @description Esta clase por medio de comunicación asíncrona llamará un service que controlará los botones que al accionarlos deben mostrar la descripción de cierto registro
 */

 class ActionDescription{
	 
	 constructor(button){
		 this.button = button;
	 }
	 
	 /**
	  * @co-author fgutierrezc@unah.hn
	  * @description Se modificó este método para que mostrara las descripciones de un registro en especifíco
	  */
	 
	 processRequest(event){
		let xhr = event.target;
		
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				try{
					let response = JSON.parse(xhr.responseText);
					if(response.status){
						let p = document.querySelector("p#contentDescription");
						p.innerHTML = "";
						p.innerHTML += `<b>Nombre del usuario</b>: ${response.nombre}<br>`;
						p.innerHTML += `<b>Cuenta del usuario</b>: ${response.cuenta}<br>`;
						p.innerHTML += `<b>Fecha de creación</b>: ${response.creacion}<br>`;
						p.innerHTML += `<b>Fecha de registro</b>: ${response.registro}<br>`;
						p.innerHTML += `<b>Contenido del twitt</b>: ${response.content}<br>`;
						p.innerHTML += `<b>Total de comentarios</b>: ${response.comments}<br>`;
						p.innerHTML += `<b>Total de shares</b>: ${response.retwitts}<br>`;
						p.innerHTML += `<b>Total de likes</b>: ${response.likes}<br>`;
						p.innerHTML += `<b>Total de visualizaciones</b>: ${response.visual}<br><br>`;
						
						let images = response.multimedia.split('URLSEPARATOR');
						
						for(let i=0; i<images.length; i++){
							try{
								let img = document.createElement("img");
								img.src = images[i];
								
								p.appendChild(img);
								p.appendChild(document.createElement("br"));
								p.appendChild(document.createElement("br"));
							}catch(ex){
								console.log("No se encontró la imagen");
							}
						}
						
						let myModal = new bootstrap.Modal("#descriptionModal", {keyboard: false});
     	 				myModal.show();
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
		xhr.open("POST", "modules/controllers/serviceDescription.jsp", true);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.onreadystatechange = this.processRequest.bind(this);
				
		xhr.send(`id=${this.button.id}`);
		
	}
	 
 }