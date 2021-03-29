package jp.co.internous.ecsite.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.ecsite.model.dao.GoodsRepository;
import jp.co.internous.ecsite.model.dao.PurchaseRepository;
import jp.co.internous.ecsite.model.dao.UserRepository;
import jp.co.internous.ecsite.model.dto.HistoryDto;
import jp.co.internous.ecsite.model.dto.LoginDto;
import jp.co.internous.ecsite.model.entity.Goods;
import jp.co.internous.ecsite.model.entity.Purchase;
import jp.co.internous.ecsite.model.entity.User;
import jp.co.internous.ecsite.model.form.CartForm;
import jp.co.internous.ecsite.model.form.HistoryForm;
import jp.co.internous.ecsite.model.form.LoginForm;

@Controller
@RequestMapping("/ecsite")
public class IndexController {
	
	@Autowired
	private UserRepository userRepos;
	
	@Autowired
	private GoodsRepository goodsRepos;
	
	@Autowired
	private PurchaseRepository purchaseRepos;
	
	// jsonとCollectionを互いに変換できる便利なクラス作成(ajaxのやり取りがjson形式だから)
	Gson gson = new Gson();
	
	@RequestMapping("/")
	public String index(Model m) {
		List<Goods> goods = goodsRepos.findAll();
		m.addAttribute("goods",goods);
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/api/login")
	public String loginApi(@RequestBody LoginForm form) {
		System.out.println("要求あり");
		List<User> users = userRepos.findByUserNameAndPassword(form.getUserName(), form.getPassword());
		// dbから値を受け取りdtoにセット？
		LoginDto dto = new LoginDto(0,null,null,"ゲスト");
		// ｄｂに存在するなら新しく作り直す
		if(users.size()>0) {
			System.out.println("ｄｂに値あり");
			dto = new LoginDto(users.get(0));
		}
		// クライアントへ　json形式にしたdtoを返す
		return gson.toJson(dto);
	}
	
	@ResponseBody
	@RequestMapping("/api/purchase")
	public String purchaseApi(@RequestBody CartForm f) {
		f.getCartList().forEach((c)->{
			long total = c.getPrice() * c.getCount();
			purchaseRepos.persist(f.getUserId(), c.getId(), c.getGoodsName(), c.getCount(), total);
			
		});
		return String.valueOf(f.getCartList().size());
	}
	
	@ResponseBody
	@RequestMapping("/api/history")
	public String historyApi(@RequestBody HistoryForm form) {
		String userId = form.getUserId();
		List<Purchase> history = purchaseRepos.findHistory(Long.parseLong(userId));
		List<HistoryDto> historyDtoList = new ArrayList<>();
		history.forEach((v)->{
			HistoryDto dto = new HistoryDto(v);
			historyDtoList.add(dto);
		});
		return gson.toJson(historyDtoList);
	}
}
