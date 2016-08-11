/* $('#orgTree').combotree({
			url: 'addOrgVic',
			loadFilter: function(rows){
				return convertOrgTree(rows);
			}
		});
 $('#editOrgTree').combotree({
		url: 'addOrgVic',
		loadFilter: function(rows){
			return convertOrgTree(rows);
		}
	});
 
 $('#userOrgTree').combotree({
		url: 'addOrgVic',
		loadFilter: function(rows){
			return convertOrgTree(rows);
		}
	});*/
 function convertOrgTree(rows){
		function exists(rows, parentOrgId){
			for(var i=0; i<rows.length; i++){
				if (rows[i].id == parentOrgId) return true;
			}
			return false;
		}	
		var nodes = [];
		// get the top level nodes
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			if (!exists(rows, row.parentOrgId)){
				nodes.push({
				    id:row.id,
					text:row.orgName,
					state:row.state,
					away:row.away,
					state:row.state,
					url:row.url,
					checked:row.checked,
					subjectType:row.subjectType
				});
			}
		}
		
		var toDo = [];
		for(var i=0; i<nodes.length; i++){
			toDo.push(nodes[i]);
		}
		while(toDo.length){
			var node = toDo.shift();	// the parent node
			// get the children nodes
			for(var i=0; i<rows.length; i++){
				var row = rows[i];
				if (row.parentOrgId == node.id){
					var child = {    
							id:row.id,
							text:row.orgName,
							state:row.state,
							away:row.away,
							state:row.state,
							url:row.url,
							checked:row.checked,
							subjectType:row.subjectType
							};
					if (node.children){
						node.children.push(child);
					} else {
						node.children = [child];
					}
					toDo.push(child);
				}
			}
		}
		return nodes;
		}