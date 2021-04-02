/**
 * Created by GaraninRI on 21.12.2015.
 */
jQuery(function($){
    $("#dateExecution").mask("9999-99-99 99:99",{placeholder:"yyyy-mm-dd HH:mm"});
});
function addElement(tableID) {

    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var colCount = table.rows[0].cells.length;

    for(var i=0; i<colCount; i++) {
        var newcell = row.insertCell(i);
        newcell.innerHTML = table.rows[0].cells[i].innerHTML;
        switch(newcell.childNodes[0].type) {
            case "select-one":
                newcell.childNodes[0].selectedIndex = 0;
                break;
        }
    }
}
function deleteRow(tableID) {
    try {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        if (rowCount>1){
            table.deleteRow(table.rows[rowCount]);
            rowCount--;
        }
    }catch(e) {
        alert(e);
    }
}