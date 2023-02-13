 document.addEventListener("change",()=>{
      let type =new Array();
       $("input[name=hometype]:checked").each(function(e){
        type.push($(this).val());
       })
      let category =new Array();
       $("input[name=homeType2]:checked").each(function(e){
        category.push($(this).val());
       });
      let bedroom = new Array();
      	$("input[name=homeBed]:checked").each(function(e){
		
		
			bedroom.push($(this).val());
			console.log(bedroom)
		
		});
      let amenities = "";
      $("input[name=homeFacilities]:checked").each(function(e){
        amenities+=$(this).val();
      })
      
      let productfiltersrc= "/search/?"
      if(type!=undefined){
        productfiltersrc+="type="+type
      }
      if(category!=undefined){
        productfiltersrc+="&category="+category
      }
      if(bedroom!=undefined){
        productfiltersrc+="&bedroom="+bedroom
      }
      if(amenities!=undefined){
        productfiltersrc+="&amenities="+amenities
      }
      $("#productfilter").attr("href",productfiltersrc)
      
    })