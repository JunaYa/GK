package gank.sin.me.gk.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gank.sin.me.gk.R;

/**
 * Created by sin on 2016/8/9.
 */

public class MarkdownView extends WebView {

    private String previewText = "<div><article class=\\\"markdown-body entry-content\\\">\\n\\n<p>AppDevKit is an iOS development library that provides foundational and developer everyday required features for their iOS app development. It has been used by Yahoo&#x2019;s iOS app production for 3 years, and future outsourcing apps will also be using AppDevKit. The stability and scalability has been verified on these production apps. It makes difficult development tasks easier and has saved 30% development time in real case. It also covers incompatibility issues caused by different iOS platforms.</p>\\n\\n<p>It has 5 major parts that include command, user interfaces, animate, image and list view support libraries. Please leverage AppDevKit in your iOS project or join our development group of AppDevKit. We will maintain this project for you.</p>\\n\\n<p>If you have any idea to improve this project, please feel free to contact with me (<strong><a href=\\\"mailto:cfsung@yahoo-inc.com\\\">cfsung@yahoo-inc.com</a></strong>) and core team (<strong><a href=\\\"mailto:app-dev-kit@yahoo-inc.com\\\">app-dev-kit@yahoo-inc.com</a></strong>) or send <strong>Pull Request</strong> to us. Thank you. </p>\\n\\n<p><a href=\\\"https://github.com/yahoo/AppDevKit/blob/master/img/AppDevKitSticker.jpg\\\" target=\\\"_blank\\\"><img src=\\\"https://github.com/yahoo/AppDevKit/raw/master/img/AppDevKitSticker.jpg\\\"></a></p>\\n\\n<h2><a id=\\\"user-content-usage\\\" class=\\\"anchor\\\" href=\\\"https://github.com/yahoo/AppDevKit#usage\\\"><svg class=\\\"octicon octicon-link\\\" height=\\\"16\\\" width=\\\"16\\\"><path></path></svg></a>Usage</h2>\\n\\n<h3><a id=\\\"user-content-installation-with-cocoapods\\\" class=\\\"anchor\\\" href=\\\"https://github.com/yahoo/AppDevKit#installation-with-cocoapods\\\"><svg class=\\\"octicon octicon-link\\\" height=\\\"16\\\" width=\\\"16\\\"><path></path></svg></a>Installation with CocoaPods</h3>\\n\\n<p>The easiest way to leverage AppDevKit is using CocoaPods. Please edit your <strong>Podfile</strong> like this:</p>\\n\\n<pre>source 'https://github.com/CocoaPods/Specs.git'  \\n\\npod 'AppDevKit'\\n</pre>\\n\\n<h3><a id=\\\"user-content-basic-usage\\\" class=\\\"anchor\\\" href=\\\"https://github.com/yahoo/AppDevKit#basic-usage\\\"><svg class=\\\"octicon octicon-link\\\" height=\\\"16\\\" width=\\\"16\\\"><path></path></svg></a>Basic Usage</h3>\\n\\n<p>Using this develop kit is very simple. First at all, import it in your any code file or just put it in prefix file (.pch). Then you will enjoy this develop kit. </p>\\n\\n<pre><code>#import &lt;AppDevKit.h&gt;\\n</code></pre>\\n\\n<h3><a id=\\\"user-content-common-tools\\\" class=\\\"anchor\\\" href=\\\"https://github.com/yahoo/AppDevKit#common-tools\\\"><svg class=\\\"octicon octicon-link\\\" height=\\\"16\\\" width=\\\"16\\\"><path></path></svg></a>Common Tools</h3>\\n\\n<ul>\\n<li><strong>ADKAppUtil</strong> &gt; The foundational tools to support common tasks.</li>\\n<li><strong>ADKStringHelper</strong> &gt; The string formatter that will generate formatted stings form date, number and etc for you.</li>\\n<li><strong>ADKCalculatorHelper</strong> &gt; The calculation set including distance, size, width, height, etc.</li>\\n<li><strong>ADKNibCacheManager</strong> &gt; The manager to cache different instances in memory and keep it as a singleton.</li>\\n<li><strong>UIView+ADKGetUIViewController</strong> &gt; Supports get any view's UIViewController.</li>\\n<li><strong>UIColor+ADKHexPresentation</strong> &gt; Supports HEX color format and color shift.</li>\\n<li><strong>ADKViewExclusiveTouch</strong> &gt; Supports exclusive touch on each sub views.</li>\\n</ul>\\n\\n<h3><a id=\\\"user-content-ui-tools\\\" class=\\\"anchor\\\" href=\\\"https://github.com/yahoo/AppDevKit#ui-tools\\\"><svg class=\\\"octicon octicon-link\\\" height=\\\"16\\\" width=\\\"16\\\"><path></path></svg></a>UI Tools</h3>\\n\\n<ul>\\n<li><strong>UIView+ADKAutoLayoutSupport</strong> &gt; Supports command autolayout features.</li>\\n<li><strong>UIScrollView+ADKPullToRefreshView</strong> &gt; Supports pull to refresh feature on scrollable view. For example: UIScrollView, UITableView and UICollectionView.</li>\\n<li><strong>UIScrollView+ADKInfiniteScrollingView</strong> &gt; Supports infinite scrolling feature on scrollable view. For example: UIScrollView, UITableView and UICollectionView.</li>\\n<li><strong>ADKModalMaskView</strong> &gt; Providing a way to create a modal view for presenting specific view.</li>\\n<li><strong>ADKGradientView</strong> &gt; Creates a gradient view for you.</li>\\n<li><strong>ADKDashedLineView</strong> &gt; Creates a dashed line around your view.</li>\\n</ul>\\n\\n<h3><a id=\\\"user-content-animation-tools\\\" class=\\\"anchor\\\" href=\\\"https://github.com/yahoo/AppDevKit#animation-tools\\\"><svg class=\\\"octicon octicon-link\\\" height=\\\"16\\\" width=\\\"16\\\"><path></path></svg></a>Animation Tools</h3>\\n\\n<ul>\\n<li><strong>UIView+ADKAnimationMacro</strong> &gt; Gives some simple animation behavior for specific UIView.</li>\\n</ul>\\n\\n<h3><a id=\\\"user-content-image-tools\\\" class=\\\"anchor\\\" href=\\\"https://github.com/yahoo/AppDevKit#image-tools\\\"><svg class=\\\"octicon octicon-link\\\" height=\\\"16\\\" width=\\\"16\\\"><path></path></svg></a>Image Tools</h3>\\n\\n<ul>\\n<li><strong>UIImage+ADKColorReplacement</strong> &gt; Supports color changing / replacement feature on UIImage.</li>\\n<li><strong>UIImage+ADKImageFilter</strong> &gt; Supports image FX, resize, crop, etc. on UIImage.</li>\\n<li><strong>UIImage+ADKDrawingTemplate</strong> &gt; Supports loss less image from a PDF source.</li>\\n</ul>\\n\\n<h3><a id=\\\"user-content-listview-tools\\\" class=\\\"anchor\\\" href=\\\"https://github.com/yahoo/AppDevKit#listview-tools\\\"><svg class=\\\"octicon octicon-link\\\" height=\\\"16\\\" width=\\\"16\\\"><path></path></svg></a>ListView Tools</h3>\\n\\n<ul>\\n<li><strong>UICollectionView+ADKOperation</strong> &gt; Supports force stop scrolling in collection view.</li>\\n<li><strong>ADKNibSizeCalculator</strong> &gt; Provides correct cell size for different devices effectively.</li>\\n<li><strong>ADKCellDynamicSizeCalculator</strong> &gt; Calculates dynamic cell with and height for UICollectionViewCell and UITableViewCell.</li>\\n<li><strong>ADKCollectionViewDynamicSizeCell</strong> &gt; Base UICollectionViewCell supports dynamic width and height features.</li>\\n<li><strong>ADKTableViewDynamicSizeCell</strong> &gt; Base UITableViewCell supports dynamic width and height features.</li>\\n</ul>\\n\\n<h3><a id=\\\"user-content-instruction\\\" class=\\\"anchor\\\" href=\\\"https://github.com/yahoo/AppDevKit#instruction\\\"><svg class=\\\"octicon octicon-link\\\" height=\\\"16\\\" width=\\\"16\\\"><path></path></svg></a>Instruction</h3>\\n\\n<ul>\\n<li><strong>Introduction of AppDevKit</strong> &gt; <a href=\\\"http://www.slideshare.net/anistarsung/appdevkit-for-ios-development\\\">http://www.slideshare.net/anistarsung/appdevkit-for-ios-development</a></li>\\n<li><strong>Sample Codes</strong> has been written in AppDevKit project. You can read code to know about \\\"How to implement these features in your project\\\". Just use git to clone AppDevKit to your local disk. It should run well with your XCode. </li>\\n<li><strong>API Reference Documents</strong> &gt; Please refer the <a href=\\\"https://yahoo.github.io/AppDevKit/\\\">gh-pages</a> in AppDevKit project.\\n<a href=\\\"https://github.com/yahoo/AppDevKit/blob/master/img/DocScreenShot.png\\\" target=\\\"_blank\\\"><img width=\\\"100%\\\" src=\\\"https://github.com/yahoo/AppDevKit/raw/master/img/DocScreenShot.png\\\"></a></li>\\n</ul>\\n\\n<h3><a id=\\\"user-content-license\\\" class=\\\"anchor\\\" href=\\\"https://github.com/yahoo/AppDevKit#license\\\"><svg class=\\\"octicon octicon-link\\\" height=\\\"16\\\" width=\\\"16\\\"><path></path></svg></a>License</h3>\\n\\n<p>This software is free to use under the Yahoo! Inc. BSD license.\\nSee the <a href=\\\"https://github.com/yahoo/AppDevKit/blob/master/LICENSE.md\\\">LICENSE</a> for license text and copyright information.</p>\\n</article>\\n  </div>";
    private static final String TAG = MarkdownView.class.getSimpleName();
    private static final String IMAGE_PATTERN = "!\\[(.*)\\]\\((.*)\\)";

    public MarkdownView(Context context) {
        super(context, null);
    }

    public MarkdownView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MarkdownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.MarkdownView);
        previewText = mTypedArray.getString(R.styleable.MarkdownView_text);
        mTypedArray.recycle();
        init();
    }

    @TargetApi(11)
    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        // default browser is not called.
        setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    loadUrl(previewText);
                } else {
                    evaluateJavascript(previewText, null);
                }
            }
        });

        loadUrl("file:///android_asset/html/md_preview.html");

        getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    /**
     * load Markdown text from file path.
     **/
    public void loadMDFilePath(String filePath) {
        loadMDFile(new File(filePath));
    }

    /**
     * load Markdown text from file.
     **/
    public void loadMDFile(File file) {
        String mdText = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String readText = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((readText = bufferedReader.readLine()) != null) {
                stringBuilder.append(readText);
                stringBuilder.append("\n");
            }
            fileInputStream.close();
            mdText = stringBuilder.toString();

        } catch (FileNotFoundException e) {
            Log.e(TAG, "FileNotFoundException:" + e);
        } catch (IOException e) {
            Log.e(TAG, "IOException:" + e);
        }
        setText(mdText);
    }

    /**
     * set show the Markdown text.
     **/
    public void setText(String text) {
        text2Mark(text);
    }

    private void text2Mark(String mdText) {

        String bs64MdText = imgToBase64(mdText);
        String escMdText = escapeForText(bs64MdText);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            previewText = String.format("javascript:preview('%s')", escMdText);

        } else {
            previewText = String.format("preview('%s')", escMdText);
        }
    }

    private String escapeForText(String mdText) {
        String escText = mdText.replace("\n", "\\\\n");
        escText = escText.replace("'", "\\\'");
        //in some cases the string may have "\r" and our view will show nothing,so replace it
        escText = escText.replace("\r", "");
        return escText;
    }

    private String imgToBase64(String mdText) {
        Pattern ptn = Pattern.compile(IMAGE_PATTERN);
        Matcher matcher = ptn.matcher(mdText);
        if (!matcher.find()) {
            return mdText;
        }

        String imgPath = matcher.group(2);
        if (isUrlPrefix(imgPath) || !isPathExChack(imgPath)) {
            return mdText;
        }
        String baseType = imgEx2BaseType(imgPath);
        if (baseType.equals("")) {
            // image load error.
            return mdText;
        }

        File file = new File(imgPath);
        byte[] bytes = new byte[(int) file.length()];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "FileNotFoundException:" + e);
        } catch (IOException e) {
            Log.e(TAG, "IOException:" + e);
        }
        String base64Img = baseType + Base64.encodeToString(bytes, Base64.NO_WRAP);

        return mdText.replace(imgPath, base64Img);
    }

    private boolean isUrlPrefix(String text) {
        return text.startsWith("http://") || text.startsWith("https://");
    }

    private boolean isPathExChack(String text) {
        return text.endsWith(".png")
                || text.endsWith(".jpg")
                || text.endsWith(".jpeg")
                || text.endsWith(".gif");
    }

    private String imgEx2BaseType(String text) {
        if (text.endsWith(".png")) {
            return "data:image/png;base64,";
        } else if (text.endsWith(".jpg") || text.endsWith(".jpeg")) {
            return "data:image/jpg;base64,";
        } else if (text.endsWith(".gif")) {
            return "data:image/gif;base64,";
        } else {
            return "";
        }
    }

}
