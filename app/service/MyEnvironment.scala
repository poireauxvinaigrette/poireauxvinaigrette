/**
 * Copyright 2012-2014 Jorge Aliss (jaliss at gmail dot com) - twitter: @jaliss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package service

import securesocial.core.RuntimeEnvironment
import securesocial.core.services.UserService
import scala.collection.immutable.ListMap
import securesocial.controllers.{MailTemplates, ViewTemplates}
import securesocial.core.authenticator._
import securesocial.core.providers._
import securesocial.core.providers.utils.{Mailer, PasswordHasher, PasswordValidator}
import securesocial.core.services._

class MyEnvironment extends RuntimeEnvironment.Default[DemoUser] {
  override val userService: UserService[DemoUser] = new InMemoryUserService()

override lazy val providers = ListMap(
	      // oauth 2 client providers
//	      include(new FacebookProvider(routes, cacheService, oauth2ClientFor(FacebookProvider.Facebook))),
//	      include(new FoursquareProvider(routes, cacheService,oauth2ClientFor(FoursquareProvider.Foursquare))),
//	      include(new GitHubProvider(routes, cacheService,oauth2ClientFor(GitHubProvider.GitHub))),
	      include(new GoogleProvider(routes, cacheService,oauth2ClientFor(GoogleProvider.Google))),
//	      include(new InstagramProvider(routes, cacheService,oauth2ClientFor(InstagramProvider.Instagram))),
	      //include(new LinkedInOAuth2Provider(routes, cacheService,oauth2ClientFor(LinkedInOAuth2Provider.LinkedIn))),
//	      include(new VkProvider(routes, cacheService,oauth2ClientFor(VkProvider.Vk))),
//	      include(new DropboxProvider(routes, cacheService, oauth2ClientFor(DropboxProvider.Dropbox))),
//	      include(new WeiboProvider(routes, cacheService, oauth2ClientFor(WeiboProvider.Weibo))),
	      // oauth 1 client providers
//	      include(new LinkedInProvider(routes, cacheService, oauth1ClientFor(LinkedInProvider.LinkedIn))),
//	      include(new TwitterProvider(routes, cacheService, oauth1ClientFor(TwitterProvider.Twitter))),
//	      include(new XingProvider(routes, cacheService, oauth1ClientFor(XingProvider.Xing))),
	      // username password
	      include(new UsernamePasswordProvider[DemoUser](userService, avatarService, viewTemplates, passwordHashers))
	    )
}