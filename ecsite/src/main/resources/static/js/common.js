	//login変数は関数
	let login =(evnt)=>{
		event.preventDefault();
		let jsonString ={
		// タグ[属性名＝値]で絞り込み.val()で値を指定
			'userName':$('input[name=userName]').val(),
			'password':$('input[name=password]').val()
		};
	// 
	$.ajax({
		type:'POST',
		url:'/ecsite/api/login',
		data:JSON.stringify(jsonString),
		contentType:'application/json',
		datatype:'json',
		scriptCharset:'utf-8'
		})
		.then((result)=>{
			let user =JSON.parse(result);
			$('#welcome').text(` --ようこそ！${user.fullName}さん`)
			$('#hiddenUserId').val(user.id);
			$('input[name=userName]').val('');
			$('input[name=userName]').val('');
			console.log('ok');
			}
		,()=>{
			console.error('error:ajax connection faild.');
			}
		);
	};
	//--------------------cart
		let addCart = (event)=>{
		let tdList = $(event.target).parent().parent().find('td');
		
		let id = $(tdList[0]).text();
		let goodsName = $(tdList[1]).text();
		let price = $(tdList[2]).text();
		let count = $(tdList[3]).find('input').val();

		if(count ==='0' || count===''){
			alert('注文数が０または空欄です。');
			return;
		}
		let cart ={
			'id':id,
			'goodsName':goodsName,
			'price':price,
			'count':count
		};

		cartList.push(cart);

		let tbody = $('#cart').find('tbody');
		$(tbody).children().remove();
		cartList.forEach(function(cart,index){
			let tr = $('<tr />');

			$('<td />',{'text':cart.id}).appendTo(tr);
			$('<td />',{'text':cart.goodsName}).appendTo(tr);
			$('<td />',{'text':cart.price}).appendTo(tr);
			$('<td />',{'text':cart.count}).appendTo(tr);
			
			let tdButton =$('<td />');
			$('<button />',{
				'text':'カート削除',
				'class':'removeBtn',
			}).appendTo(tdButton);
			
			$(tdButton).appendTo(tr);
			$(tr).appendTo(tbody);
		});
		$('.removeBtn').on('click',removeCart);
	};
//----------------------------buy
let buy =(event)=>{
	// 値を作るのではなくｄｂに入っているカートに購入の許可を出すだけなのですぐ通信
	$.ajax({
		type:'POST',
		url:'/ecsite/api/purchase',
		data:JSON.stringify({
			"userId":$('#hiddenUserId').val(),
			"cartList":cartList
		}),
		contentType:'application/json',
		datatype:'json',
		scriptCharset:'utf-8'
	})
	.then((result)=>{
		alert('購入しました');
	},()=>{
		console.error('error:ajax connection failed.');
	}
	);
};

//----------------------------show history
let showHistory = () =>{
	$.ajax({
		type:'POST',
		url:'/ecsite/api/history',
		data: JSON.stringify({"userId":$('#hiddenUserId').val()}),
		contentType:'application/json',
		datatype:'utf-8'
	})
	.then((result)=>{
		let historyList = JSON.parse(result);
		let tbody = $('#historyTable').find('tbody');
		$(tbody).children().remove();
		historyList.forEach((history,index)=>{
			let tr=$('<tr />');
			
			$('<td />',{'text':history.goodsName}).appendTo(tr);
			$('<td />',{'text':history.itemCount}).appendTo(tr);
			$('<td />',{'text':history.createdAt}).appendTo(tr);
			
			$(tr).appendTo(tbody);
		});
		$("#history").dialog("open");
	},()=>{
		console.error('error:ajax connection failed.');
	}
	);
};
//---------------------cart remove
let removeCart=(event)=>{
	const tdList = $(event.target).parent().parent().find(td);
	let id = $(tdList[0]).text();
	coartList =cartList.filter(function(cart){
		return cart.id !== id;
	});
	$(event.target).parent().parent().remove();
};