/**
 * 
 */


		//Script Jquery per JqueryUI


		$(document).ready(function() {

			reloadJqueryUI();

		});

		function reloadJqueryUI() {

			$(".date").datepicker();

			$(".quantita").spinner({
				min : 0.1,
				step : 0.1,
				numberFormat : "n"
			});
			$(".incasso").spinner({
				min : 0.5,
				step : 0.5,
				numberFormat : "c"
			});

			$(".menuTendina").selectmenu();

			$(".button").button();

			$('.accordion').accordion();
			
			$('#menu').menu({
			      items: "> :not(.ui-widget-header)"
		    });

		}