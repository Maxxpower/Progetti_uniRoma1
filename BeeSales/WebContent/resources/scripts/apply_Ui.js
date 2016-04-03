/**
 * 
 */

		//Script Jquery per JqueryUI

		jQuery.noConflict();
		jQuery(document).ready(function() {

			reloadJqueryUI();

		});

		function reloadJqueryUI() {

			jQuery(".date").datepicker();

			jQuery(".quantita").spinner({
				min : 0.1,
				step : 0.1,
				numberFormat : "n"
			});
			jQuery(".incasso").spinner({
				min : 0.5,
				step : 0.5,
				numberFormat : "c"
			});

			jQuery(".menuTendina").selectmenu();

			jQuery(".button").button();

			jQuery('.accordion').accordion();
			
			jQuery('#menu').menu({
			      items: "> :not(.ui-widget-header)"
		    });

		}