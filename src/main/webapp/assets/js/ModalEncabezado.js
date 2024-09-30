/**
 * @author fgutierrezc@unah.hn
 * @version 0.1.0
 * @date 28/04/2023
 * @description Esta clase se encarga de llamar a las distintas modales que están en el encabezado
 */

 class ModalEncabezado{
	 constructor(idModal1, idModal2, idModal3){
		 this.idModal1 = idModal1;
	 	 this.idModal2 = idModal2;
	 	 this.idModal3 = idModal3;
	 }
	 
	 showCreateModal(event){
		 let myModal = new bootstrap.Modal(`#${this.idModal1}`, {keyboard: false})
     	 myModal.show();
	 }
	 
	 showEliminateModal(event){
		 let myModal = new bootstrap.Modal(`#${this.idModal2}`, {keyboard: false})
     	 myModal.show();
	 }
	 
	 showAutorModal(event){
		 let myModal = new bootstrap.Modal(`#${this.idModal3}`, {keyboard: false})
     	 myModal.show();
	 }
 }
 
 