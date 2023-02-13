    function setThumbnail(event) {
        var reader = new FileReader();
	
        reader.onload = function(event) {
          document.querySelector("#previewimg").src = event.target.result;
          
        };
		
        reader.readAsDataURL(event.target.files[0]);
      }
