
    function CreateTableFromJSON(data2) {
        var data =data2;
        
        alert(data);
        var temp ;
        for(var i = 0, len = data.length; i < data.length; i++) {
        	temp = $('<tr/>');
             temp = '<td>' + data[i].COMPONENTNAME + '</td>';
            temp+= '<td>' + data[i].COMPONENTVALUE+ '</td>';
            $('#showData').append(temp);
        }
    }
