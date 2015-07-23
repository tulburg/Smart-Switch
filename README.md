# Smart-Switch
Android multi choice switch view (Used this in my project, hope you find it useful)
<br/><br/>
<img src="screenshot.png" height="430px" />
<h1>Features</h1>
<ol style="list-style:decimal">
  <li>Two Styles, Box or Round</li>
  <li>Border Color</li>
  <li>Corner Radius</li>
  <li>Full Color Customizable</li>
</ol>
<h3>Usage</h3>
<ul style="list-style:decimal">
  <li>In XML Layout </li>
  <code>&lt;com.tmagic.smartswitch.Switch</code><br/>
  <code>android:id="@+id/switch_me"</code><br/>
  <code>android:layout_width="wrap_content"</code> <br>
  <code>android:layout_centerHorizontal="true"</code> <br>
  <code>android:layout_centerVertical="true"</code><br>
  <code>android:layout_height="wrap_content"</code><br>
  <code>app:activeColor="@color/blue"</code><br>
  <code>app:activeTextColor="#fff"</code><br>
  <code>app:inActiveColor="#fff"</code><br>
  <code>app:inactiveTextColor="#000"</code><br>
  <code>app:cornerRadius="6"</code><br>
  <code>app:strokeWidth="2"&gt;</code><br>
  Just add your buttons here according to your specifications
  <p><code>&lt;/com.tmagic.smartswitch.Switch&gt;</code></p>
  
  <li>Programatically</li>
  <code>Switch newSwitch = new Switch(this);</code><br/>
  <code>Button optionTall = new Button(this);</code><br/>
  <code>Button optionShort = new Button(this);</code><br/>
  <code>Button optionMedium = new Button(this);</code><br/>
  <code>optionTall.setText("Tall");</code><br/>
  <code>optionShort.setText("Short");</code><br/>
  <code>optionMedium.setText("Medium");</code><br/>
  <code>newSwitch.addView(optionTall);</code><br/>
  <code>newSwitch.addView(optionMedium);</code><br/>
  <code>newSwitch.addView(optionShort);</code><br/>
  <code>newSwitch.setStyle(Switch.STYLE.ROUND);</code><br/>
  <code>newSwitch.setActiveColor(getResources().getColor(R.color.black));</code><br/>
  <code>newSwitch.setInActiveColor(getResources().getColor(R.color.white));</code><br/>
  <code>newSwitch.setActiveTextColor(getResources().getColor(R.color.white));</code><br/>
  <code>newSwitch.setInactiveTextColor(getResources().getColor(R.color.black));</code><br/>
  <code>newSwitch.setSelectedIndex(2);</code><br/>

  
</ul>


