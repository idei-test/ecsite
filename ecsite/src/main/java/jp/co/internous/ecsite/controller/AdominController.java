package jp.co.internous.ecsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.ecsite.model.dao.GoodsRepository;
import jp.co.internous.ecsite.model.dao.UserRepository;
import jp.co.internous.ecsite.model.entity.Goods;
import jp.co.internous.ecsite.model.entity.User;
import jp.co.internous.ecsite.model.form.GoodsForm;
import jp.co.internous.ecsite.model.form.LoginForm;

@Controller
@RequestMapping("/ecsite/admin")
public class AdominController {
	@Autowired//自動配線の意味
	private UserRepository userRepository;
	@Autowired
	private GoodsRepository goodsRepository;
	
	@RequestMapping("/")//管理者トップページ表示
	public String index() {
		return "adminindex";
	}
	
	@PostMapping("/welcome")//管理者管理画面表示　ｄｂから管理者か判断して必要なものを表示
	public String welcome(LoginForm form,Model m) {
		// 自動で作られたuserリポジトリを、インターフェースで定義したメソッドと、formのメソッドで操作
		List<User> users = userRepository.findByUserNameAndPassword(form.getUserName(), form.getPassword());
		// userの値が一つ以上あるか？
		if(users != null && users.size()>0) {
			// 管理者か判定
			boolean isAdmin = users.get(0).getIsAdmin() != 0;
			if(isAdmin) {
				// adminではないなら属性がなく表示されない
				List<Goods> goods = goodsRepository.findAll();
				m.addAttribute("userName",users.get(0).getUserName());
				m.addAttribute("password",users.get(0).getPassword());
				m.addAttribute("goods",goods);
			}
		}

		return "welcome";
	}
	
	@RequestMapping("/goodsMst")
	public String goodsMst(LoginForm form, Model m) {
		m.addAttribute("userName",form.getUserName());
		m.addAttribute("password",form.getPassword());
		return "goodsmst";
	}
	
	@RequestMapping("/addGoods")
	public String addGoods(GoodsForm goodsForm,LoginForm loginForm,Model m) {
		m.addAttribute("userName",loginForm.getUserName());
		m.addAttribute("password",loginForm.getPassword());
		
		Goods goods = new Goods();
		goods.setGoodsName(goodsForm.getGoodsName());
		goods.setPrice(goodsForm.getPrice());
		goodsRepository.saveAndFlush(goods);
		return "forward:/ecsite/admin/welcome";
	}
	@ResponseBody
	@RequestMapping("/api/deleteGoods")
	public String deleteApi(@RequestBody GoodsForm f, Model m) {
		try {
			goodsRepository.deleteById(f.getId());
		}catch(IllegalArgumentException e){
		//不正な引数、または不適切な引数をメソッドに渡したことを示すためにスローされます。
		// 例えば　存在しないIDを消そうとするとき
			return "-1";
		}
		return "1";
	}
}
