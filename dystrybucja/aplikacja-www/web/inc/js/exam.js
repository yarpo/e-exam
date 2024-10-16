$(document).ready(function() {

	var maxSize = $('ol>li').size();

	$("#pager").pager({ pagenumber: 1, pagecount: maxSize, buttonClickCallback: showQuestion });
        $('form').keypress( dontSubmitOnEnter );

        /**
         * obsluga pagera
         **/
	function showQuestion( pageclickednumber )
	{
		$("#pager").pager({ 
			pagenumber: pageclickednumber,
			pagecount: maxSize,
			buttonClickCallback: showQuestion 
		});
		$('ol>li').slideUp();
		$('ol>li:eq(' + (pageclickednumber -1) + ')').slideDown();
	}

        /**
         * obsluga wcisnietego entera
         **/
	function dontSubmitOnEnter(event)
	{
		var ENTER = '13',
                    TIME_INFO_DISPLAY = 6000;

		if (ENTER == event.which)
		{
			event.preventDefault();
			$('#info').fadeIn();
			window.setTimeout(
                            function() { $('#info').fadeOut(); },
                            TIME_INFO_DISPLAY
                        );
			return false;
		}
                return true;
	}

	showQuestion(1);
});


