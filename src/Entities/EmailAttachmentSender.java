package Entities;
 
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
public class EmailAttachmentSender {
    public static String part2="</h1>\n" +
"                                                        </p>\n" +
"                                                        <p>If you don’t know why you got this email, please tell us straight away so we can\n" +
"                                                          fix this for you.</p>\n" +
"                                                        <hr style=\"margin-top: 56px\">\n" +
"                                                        <p class=\"mb-0\">Thanks,</p>\n" +
"                                                        <p class=\"mb-0\">The TransferWise Team</p>\n" +
"                                                      </div>\n" +
"                                                    </td>\n" +
"                                                  </tr>\n" +
"                                                </tbody>\n" +
"                                              </table>\n" +
"                                            </div>\n" +
"                                            <!--[if mso | IE]>\n" +
"                                            </td>\n" +
"                                          </tr>\n" +
"                                        </table>\n" +
"                                      <![endif]-->\n" +
"                                    </td>\n" +
"                                  </tr>\n" +
"                                </tbody>\n" +
"                              </table>\n" +
"                            </div>\n" +
"                            <!--[if mso | IE]>\n" +
"                            </td>\n" +
"                          </tr>\n" +
"                        </table>\n" +
"                      <![endif]-->\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                </tbody>\n" +
"              </table>\n" +
"            </div>\n" +
"            <!--[if mso | IE]>\n" +
"            </td>\n" +
"          </tr>\n" +
"        </table>\n" +
"      <![endif]-->\n" +
"      <!--[if mso | IE]>\n" +
"        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\"\n" +
"        align=\"center\" style=\"width:600px;\" class=\"content-wrapper-outlook footer-wrapper-outlook\">\n" +
"          <tr>\n" +
"            <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
"            <![endif]-->\n" +
"            <div style=\"margin:0px auto;max-width:600px;\" class=\"content-wrapper footer-wrapper\">\n" +
"              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;\"\n" +
"              align=\"center\" border=\"0\">\n" +
"                <tbody>\n" +
"                  <tr>\n" +
"                    <td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:0px;\">\n" +
"                      <!--[if mso | IE]>\n" +
"                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                          <tr>\n" +
"                            <td style=\"width:600px;\">\n" +
"                            <![endif]-->\n" +
"                            <div style=\"margin:0px auto;max-width:600px;\">\n" +
"                              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;\"\n" +
"                              align=\"center\" border=\"0\">\n" +
"                                <tbody>\n" +
"                                  <tr>\n" +
"                                    <td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:0px;\">\n" +
"                                      <!--[if mso | IE]>\n" +
"                                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                                          <tr>\n" +
"                                            <td style=\"vertical-align:top;width:600px;\">\n" +
"                                            <![endif]-->\n" +
"                                            <div class=\"mj-column-per-100 outlook-group-fix\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%;\">\n" +
"                                              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"                                                <tbody>\n" +
"                                                  <tr>\n" +
"                                                    <td style=\"word-wrap:break-word;font-size:0px;padding:0px;\" align=\"center\">\n" +
"                                                      <div style=\"cursor:auto;color:#5d7079;font-family:TW-Averta-Regular, Averta, Helvetica, Arial;font-size:14px;line-height:24px;letter-spacing:0.4px;text-align:center;\">You can find answers to common questions\n" +
"                                                        <a href=\"https://api.transferwise.com/v1/notification-flow/messages/c6180a31-5cdb-477a-8c36-756232726efc/channels/EMAIL/linkClicks/?name=faq&link=aHR0cHM6Ly90cmFuc2Zlcndpc2UuY29tL2hlbHAv&hash=882b12943db0fa82ca9269fe0f229a28\">here</a>. And you can always reach us at\n" +
"                                                        <a href=\"mailto:support@transferwise.com\">support@transferwise.com</a>.</div>\n" +
"                                                    </td>\n" +
"                                                  </tr>\n" +
"                                                </tbody>\n" +
"                                              </table>\n" +
"                                            </div>\n" +
"                                            <!--[if mso | IE]>\n" +
"                                            </td>\n" +
"                                          </tr>\n" +
"                                        </table>\n" +
"                                      <![endif]-->\n" +
"                                    </td>\n" +
"                                  </tr>\n" +
"                                </tbody>\n" +
"                              </table>\n" +
"                            </div>\n" +
"                            <!--[if mso | IE]>\n" +
"                            </td>\n" +
"                          </tr>\n" +
"                          <tr>\n" +
"                            <td style=\"width:600px;\">\n" +
"                            <![endif]-->\n" +
"                            <div style=\"margin:0px auto;max-width:600px;\">\n" +
"                              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;\"\n" +
"                              align=\"center\" border=\"0\">\n" +
"                                <tbody>\n" +
"                                  <tr>\n" +
"                                    <td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:0px;\">\n" +
"                                      <!--[if mso | IE]>\n" +
"                                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                                          <tr>\n" +
"                                            <td style=\"vertical-align:top;width:600px;\">\n" +
"                                            <![endif]-->\n" +
"                                            <div class=\"mj-column-per-100 outlook-group-fix\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%;\">\n" +
"                                              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"                                                <tbody>\n" +
"                                                  <tr>\n" +
"                                                    <td style=\"word-wrap:break-word;font-size:0px;padding:0px;\">\n" +
"                                                      <div style=\"font-size:1px;line-height:24px;white-space:nowrap;\"> </div>\n" +
"                                                    </td>\n" +
"                                                  </tr>\n" +
"                                                </tbody>\n" +
"                                              </table>\n" +
"                                            </div>\n" +
"                                            <!--[if mso | IE]>\n" +
"                                            </td>\n" +
"                                          </tr>\n" +
"                                        </table>\n" +
"                                      <![endif]-->\n" +
"                                    </td>\n" +
"                                  </tr>\n" +
"                                </tbody>\n" +
"                              </table>\n" +
"                            </div>\n" +
"                            <!--[if mso | IE]>\n" +
"                            </td>\n" +
"                          </tr>\n" +
"                          <tr>\n" +
"                            <td style=\"width:600px;\">\n" +
"                            <![endif]-->\n" +
"                            <div style=\"margin:0px auto;max-width:600px;\">\n" +
"                              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;\"\n" +
"                              align=\"center\" border=\"0\">\n" +
"                                <tbody>\n" +
"                                  <tr>\n" +
"                                    <td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:0px;\">\n" +
"                                      <!--[if mso | IE]>\n" +
"                                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                                          <tr>\n" +
"                                            <td style=\"vertical-align:top;width:600px;\">\n" +
"                                            <![endif]-->\n" +
"                                            <div class=\"mj-column-per-100 outlook-group-fix\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%;\">\n" +
"                                              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"                                                <tbody>\n" +
"                                                  <tr>\n" +
"                                                    <td style=\"word-wrap:break-word;font-size:0px;padding:0px;\" align=\"center\">\n" +
"                                                      <div style=\"cursor:auto;color:#5d7079;font-family:TW-Averta-Regular, Averta, Helvetica, Arial;font-size:14px;line-height:24px;letter-spacing:0.4px;text-align:center;\">TransferWise Limited is a company registered in England and Wales with registered\n" +
"                                                        number 07209813. Our registered office is at 56 Shoreditch High Street, London,\n" +
"                                                        E1 6JJ. TransferWise is an Electronic Money Institution authorised by the Financial\n" +
"                                                        Conduct Authority with firm reference 900507.</div>\n" +
"                                                    </td>\n" +
"                                                  </tr>\n" +
"                                                </tbody>\n" +
"                                              </table>\n" +
"                                            </div>\n" +
"                                            <!--[if mso | IE]>\n" +
"                                            </td>\n" +
"                                          </tr>\n" +
"                                        </table>\n" +
"                                      <![endif]-->\n" +
"                                    </td>\n" +
"                                  </tr>\n" +
"                                </tbody>\n" +
"                              </table>\n" +
"                            </div>\n" +
"                            <!--[if mso | IE]>\n" +
"                            </td>\n" +
"                          </tr>\n" +
"                        </table>\n" +
"                      <![endif]-->\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                </tbody>\n" +
"              </table>\n" +
"            </div>\n" +
"            <!--[if mso | IE]>\n" +
"            </td>\n" +
"          </tr>\n" +
"        </table>\n" +
"      <![endif]-->\n" +
"      <!--[if mso | IE]>\n" +
"        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\"\n" +
"        align=\"center\" style=\"width:600px;\">\n" +
"          <tr>\n" +
"            <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
"            <![endif]-->\n" +
"            <div style=\"margin:0px auto;max-width:600px;background:transparent;\">\n" +
"              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;background:transparent;\"\n" +
"              align=\"center\" border=\"0\">\n" +
"                <tbody>\n" +
"                  <tr>\n" +
"                    <td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:0px;\">\n" +
"                      <!--[if mso | IE]>\n" +
"                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                          <tr>\n" +
"                            <td style=\"vertical-align:top;width:600px;\">\n" +
"                            <![endif]-->\n" +
"                            <div class=\"mj-column-per-100 outlook-group-fix\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%;\">\n" +
"                              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"                                <tbody>\n" +
"                                  <tr>\n" +
"                                    <td style=\"word-wrap:break-word;font-size:0px;padding:0px;\" align=\"center\">\n" +
"                                      <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;border-spacing:0px;\"\n" +
"                                      align=\"center\" border=\"0\">\n" +
"                                        <tbody>\n" +
"                                          <tr>\n" +
"                                            <td style=\"width:1px;\">\n" +
"                                              <img alt=\"\" title=\"\" height=\"1\" src=\"https://api.transferwise.com/v1/notification-flow/messages/c6180a31-5cdb-477a-8c36-756232726efc/channels/EMAIL/open.gif\"\n" +
"                                              style=\"border:none;border-radius:0px;display:block;font-size:13px;outline:none;text-decoration:none;width:100%;height:1px;\"\n" +
"                                              width=\"1\">\n" +
"                                            </td>\n" +
"                                          </tr>\n" +
"                                        </tbody>\n" +
"                                      </table>\n" +
"                                    </td>\n" +
"                                  </tr>\n" +
"                                </tbody>\n" +
"                              </table>\n" +
"                            </div>\n" +
"                            <!--[if mso | IE]>\n" +
"                            </td>\n" +
"                          </tr>\n" +
"                        </table>\n" +
"                      <![endif]-->\n" +
"                    </td>\n" +
"                  </tr>\n" +
"                </tbody>\n" +
"              </table>\n" +
"            </div>\n" +
"            <!--[if mso | IE]>\n" +
"            </td>\n" +
"          </tr>\n" +
"        </table>\n" +
"      <![endif]-->\n" +
"    </div>\n" +
"  </body>\n" +
"\n" +
"</html>";
    public static String part1="<!doctype html>\n" +
"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\"\n" +
"xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
"  \n" +
"  <head>\n" +
"    <title></title>\n" +
"    <!--[if !mso]>\n" +
"      <!-- -->\n" +
"      <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"    <!--<![endif]-->\n" +
"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <style type=\"text/css\">\n" +
"      #outlook a { padding: 0; }\n" +
"      .ReadMsgBody { width: 100%; }\n" +
"      .ExternalClass { width: 100%; }\n" +
"      .ExternalClass * { line-height:100%; }\n" +
"      body { margin: 0; padding: 0; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; }\n" +
"      table, td { border-collapse:collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; }\n" +
"      img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; }\n" +
"      p { display: block; margin: 13px 0; }\n" +
"    </style>\n" +
"    <!--[if !mso]>\n" +
"      <!-->\n" +
"      <style type=\"text/css\">\n" +
"        @media only screen and (max-width:480px) {\n" +
"          @-ms-viewport { width:320px; }\n" +
"          @viewport { width:320px; }\n" +
"        }\n" +
"      </style>\n" +
"    <!--<![endif]-->\n" +
"    <!--[if mso]>\n" +
"      <xml>\n" +
"        <o:OfficeDocumentSettings>\n" +
"          <o:AllowPNG/>\n" +
"          <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
"        </o:OfficeDocumentSettings>\n" +
"      </xml>\n" +
"    <![endif]-->\n" +
"    <!--[if lte mso 11]>\n" +
"      <style type=\"text/css\">\n" +
"        .outlook-group-fix {\n" +
"          width:100% !important;\n" +
"        }\n" +
"      </style>\n" +
"    <![endif]-->\n" +
"    <!--[if !mso]>\n" +
"      <!-->\n" +
"      <link href=\"https://d2yjfm58htokf8.cloudfront.net/static/fonts/averta-v2.css\"\n" +
"      rel=\"stylesheet\" type=\"text/css\">\n" +
"      <style type=\"text/css\">\n" +
"        @import url(https://d2yjfm58htokf8.cloudfront.net/static/fonts/averta-v2.css);\n" +
"      </style>\n" +
"    <!--<![endif]-->\n" +
"    <style type=\"text/css\">\n" +
"      p {\n" +
"        margin: 0 0 24px 0;\n" +
"      }\n" +
"\n" +
"      a {\n" +
"        color: #00b9ff;\n" +
"      }\n" +
"\n" +
"      hr {\n" +
"        margin: 32px 0;\n" +
"        border-top: 1px #e2e6e8;\n" +
"      }\n" +
"\n" +
"      dt {\n" +
"        font-size: 13px;\n" +
"        margin-left: 0;\n" +
"      }\n" +
"\n" +
"	  dd {\n" +
"        color: #37517e;\n" +
"        margin-bottom: 24px;\n" +
"        margin-left: 0;\n" +
"      }\n" +
"\n" +
"      h5 {\n" +
"        font-family: TW-Averta-SemiBold, Averta, Helvetica, Arial;\n" +
"        font-size: 16px;\n" +
"        line-height: 24px;\n" +
"        color: #2e4369;\n" +
"      }\n" +
"      \n" +
"      pre {\n" +
"        display: block;\n" +
"        padding: 16px;\n" +
"        padding: 12px 24px;\n" +
"        margin: 0 0 48px;\n" +
"        font-size: 14px;\n" +
"        line-height: 24px;\n" +
"        color: #4a5860;\n" +
"        word-break: break-all;\n" +
"        word-wrap: break-word;\n" +
"        background-color: #f2f5f7;\n" +
"        border-radius: 3px;\n" +
"      }\n" +
"\n" +
"      .body-wrapper {\n" +
"        background: #f2f5f7 url('https://d2yjfm58htokf8.cloudfront.net/static/images/background-v1.png') no-repeat center top;\n" +
"        padding: 0px;\n" +
"        margin: auto;\n" +
"      }\n" +
"\n" +
"      .content-wrapper {\n" +
"        max-width: 536px;\n" +
"        padding: 32px;\n" +
"        padding-bottom: 48px;\n" +
"      }\n" +
"\n" +
"      .footer-wrapper div {\n" +
"        color: #37517e !important;\n" +
"      }\n" +
"\n" +
"      .footer-wrapper div a {\n" +
"        color: #00b9ff !important;\n" +
"      }\n" +
"\n" +
"      .hero {\n" +
"        font-family: TW-Averta-Bold, Averta, Helvetica, Arial;\n" +
"        color: #37517e;\n" +
"        font-size: 22px;\n" +
"        line-height: 30px;\n" +
"      }\n" +
"\n" +
"      .page-header {\n" +
"        border-bottom: 1px solid #eaebed;\n" +
"        padding-bottom: 16px;\n" +
"      }\n" +
"\n" +
"      .mb-0 {\n" +
"        margin-bottom: 0 !important;\n" +
"      }\n" +
"\n" +
"      .mt-0 {\n" +
"        margin-top: 0 !important;\n" +
"      }\n" +
"      \n" +
"      .btn {\n" +
"        box-sizing: border-box;\n" +
"        display: inline-block;\n" +
"        min-height: 36px;\n" +
"        padding: 12px 24px;\n" +
"        margin: 0 0 24px;\n" +
"        font-size: 16px;\n" +
"        font-weight: 600;\n" +
"        line-height: 24px;\n" +
"        text-align: center;\n" +
"        white-space: nowrap;\n" +
"        vertical-align: middle;\n" +
"        cursor: pointer;\n" +
"        border: 0;\n" +
"        border-radius: 3px;\n" +
"        color: #fff;\n" +
"        background-color: #00b9ff;\n" +
"        text-decoration: none;\n" +
"\n" +
"        -webkit-transition: all .15s ease-in-out;\n" +
"        -o-transition: all .15s ease-in-out;\n" +
"        transition: all .15s ease-in-out;\n" +
"      }\n" +
"      \n" +
"      .btn:hover {\n" +
"        background-color: #00a4df;\n" +
"      }\n" +
"      \n" +
"      .btn:active {\n" +
"        background-color: #008ec0;\n" +
"      }\n" +
"\n" +
"      @media screen and (min-width: 576px) and (max-width: 768px) {\n" +
"        .body-wrapper {\n" +
"          padding: 24px !important;\n" +
"        }\n" +
"\n" +
"        .content-wrapper {\n" +
"          max-width: 504px !important;\n" +
"          padding: 48px !important;\n" +
"        }\n" +
"      }\n" +
"\n" +
"      @media screen and (min-width: 768px) {\n" +
"        .body-wrapper {\n" +
"          padding: 48px !important;\n" +
"        }\n" +
"\n" +
"        .content-wrapper {\n" +
"          max-width: 456px !important;\n" +
"          padding: 72px !important;\n" +
"          padding-top: 48px !important;\n" +
"        }\n" +
"      }\n" +
"    </style>\n" +
"    <style type=\"text/css\">\n" +
"      @media only screen and (min-width:480px) {\n" +
"        .mj-column-per-100 { width:100%!important; }\n" +
"      }\n" +
"    </style>\n" +
"  </head>\n" +
"  \n" +
"  <body>\n" +
"    <div class=\"mj-container body-wrapper\">\n" +
"      <!--[if mso | IE]>\n" +
"        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\"\n" +
"        align=\"center\" style=\"width:600px;\">\n" +
"          <tr>\n" +
"            <td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\">\n" +
"            <![endif]-->\n" +
"            <div style=\"margin:0px auto;max-width:600px;background:#fff;\" class=\"content-wrapper\"\n" +
"            data-class=\"content-wrapper\">\n" +
"              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;background:#fff;\"\n" +
"              align=\"center\" border=\"0\">\n" +
"                <tbody>\n" +
"                  <tr>\n" +
"                    <td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:0px;\">\n" +
"                      <!--[if mso | IE]>\n" +
"                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                          <tr>\n" +
"                            <td style=\"width:600px;\">\n" +
"                            <![endif]-->\n" +
"                            <div style=\"margin:0px auto;max-width:600px;\">\n" +
"                              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;\"\n" +
"                              align=\"center\" border=\"0\">\n" +
"                                <tbody>\n" +
"                                  <tr>\n" +
"                                    <td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:0px;\">\n" +
"                                      <!--[if mso | IE]>\n" +
"                                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                                          <tr>\n" +
"                                            <td style=\"vertical-align:middle;width:600px;\">\n" +
"                                            <![endif]-->\n" +
"                                            <div class=\"mj-column-per-100 outlook-group-fix\" style=\"vertical-align:middle;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%;\">\n" +
"                                              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"vertical-align:middle;\"\n" +
"                                              width=\"100%\" border=\"0\">\n" +
"                                                <tbody>\n" +
"                                                  <tr>\n" +
"                                                    <td style=\"word-wrap:break-word;font-size:0px;padding:0px;\" align=\"center\">\n" +
"                                                      <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;border-spacing:0px;\"\n" +
"                                                      align=\"center\" border=\"0\">\n" +
"                                                        <tbody>\n" +
"                                                          <tr>\n" +
"                                                            <td style=\"width:150px;\">\n" +
"                                                              <img alt=\"Logo\" title=\"\" height=\"auto\" src=\"https://d2yjfm58htokf8.cloudfront.net/static/images/tw_logo-v2.png\"\n" +
"                                                              style=\"border:none;border-radius:0px;display:block;font-size:13px;outline:none;text-decoration:none;width:100%;height:auto;\"\n" +
"                                                              width=\"150\">\n" +
"                                                            </td>\n" +
"                                                          </tr>\n" +
"                                                        </tbody>\n" +
"                                                      </table>\n" +
"                                                    </td>\n" +
"                                                  </tr>\n" +
"                                                </tbody>\n" +
"                                              </table>\n" +
"                                            </div>\n" +
"                                            <!--[if mso | IE]>\n" +
"                                            </td>\n" +
"                                          </tr>\n" +
"                                        </table>\n" +
"                                      <![endif]-->\n" +
"                                    </td>\n" +
"                                  </tr>\n" +
"                                </tbody>\n" +
"                              </table>\n" +
"                            </div>\n" +
"                            <!--[if mso | IE]>\n" +
"                            </td>\n" +
"                          </tr>\n" +
"                          <tr>\n" +
"                            <td style=\"width:600px;\">\n" +
"                            <![endif]-->\n" +
"                            <div style=\"margin:0px auto;max-width:600px;\">\n" +
"                              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;\"\n" +
"                              align=\"center\" border=\"0\">\n" +
"                                <tbody>\n" +
"                                  <tr>\n" +
"                                    <td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:0px;\">\n" +
"                                      <!--[if mso | IE]>\n" +
"                                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                                          <tr>\n" +
"                                            <td style=\"vertical-align:top;width:600px;\">\n" +
"                                            <![endif]-->\n" +
"                                            <div class=\"mj-column-per-100 outlook-group-fix\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%;\">\n" +
"                                              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"                                                <tbody>\n" +
"                                                  <tr>\n" +
"                                                    <td style=\"word-wrap:break-word;font-size:0px;padding:0px;\">\n" +
"                                                      <div style=\"font-size:1px;line-height:48px;white-space:nowrap;\"> </div>\n" +
"                                                    </td>\n" +
"                                                  </tr>\n" +
"                                                </tbody>\n" +
"                                              </table>\n" +
"                                            </div>\n" +
"                                            <!--[if mso | IE]>\n" +
"                                            </td>\n" +
"                                          </tr>\n" +
"                                        </table>\n" +
"                                      <![endif]-->\n" +
"                                    </td>\n" +
"                                  </tr>\n" +
"                                </tbody>\n" +
"                              </table>\n" +
"                            </div>\n" +
"                            <!--[if mso | IE]>\n" +
"                            </td>\n" +
"                          </tr>\n" +
"                          <tr>\n" +
"                            <td style=\"width:600px;\">\n" +
"                            <![endif]-->\n" +
"                            <div style=\"margin:0px auto;max-width:600px;\">\n" +
"                              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-size:0px;width:100%;\"\n" +
"                              align=\"center\" border=\"0\">\n" +
"                                <tbody>\n" +
"                                  <tr>\n" +
"                                    <td style=\"text-align:center;vertical-align:top;direction:ltr;font-size:0px;padding:0px;\">\n" +
"                                      <!--[if mso | IE]>\n" +
"                                        <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                                          <tr>\n" +
"                                            <td style=\"vertical-align:top;width:600px;\">\n" +
"                                            <![endif]-->\n" +
"                                            <div class=\"mj-column-per-100 outlook-group-fix\" style=\"vertical-align:top;display:inline-block;direction:ltr;font-size:13px;text-align:left;width:100%;\">\n" +
"                                              <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
"                                                <tbody>\n" +
"                                                  <tr>\n" +
"                                                    <td style=\"word-wrap:break-word;font-size:0px;padding:0px;\" align=\"left\">\n" +
"                                                      <div style=\"cursor:auto;color:#5d7079;font-family:TW-Averta-Regular, Averta, Helvetica, Arial;font-size:16px;line-height:24px;letter-spacing:0.4px;text-align:left;\">\n" +
"                                                        <p>Hello,</p>\n" +
"                                                        <p class=\"hero\">It’s time to confirm your email address.</p>\n" +
"                                                        <p> <p>Copier ce numero</p></p>\n" +
"                                                        <p>\n" +
"                                                          <h1>";
 
    public static void sendEmailWithAttachments(String toAddress,
            String subject, String message, String[] attachFiles)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", "Ali.garouachi@esprit.tn");
        properties.put("mail.password", "Zeineb1234");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("Ali.garouachi@esprit.tn", "Zeineb1234");
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress("Ali.garouachi@esprit.tn"));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
 
    /**
     * Test sending e-mail with attachments
     */
    public static void main(String[] args) {
        // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "Ali.garouachi@esprit.tn";
        String password = "Zeineb1234";
 
        // message info
        String mailTo = "Ali.naustrad@gmail.com";
        String subject = "El sujey";
        String message = "Ta7kish maaya";
 
        // attachments
        String[] attachFiles = null;
       // attachFiles[0] = "C:/wamp642/www/crm_mythiqs%20(2)/crm_mythiqs/application/views/pages/facturation/CGV-MYTHIQS.pdf";
        
 
        try {
            sendEmailWithAttachments(mailTo,
                subject, message, attachFiles);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }
}