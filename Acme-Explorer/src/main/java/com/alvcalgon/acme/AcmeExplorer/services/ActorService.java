package com.alvcalgon.acme.AcmeExplorer.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alvcalgon.acme.AcmeExplorer.bean.Actor;
import com.alvcalgon.acme.AcmeExplorer.bean.Authority;
import com.alvcalgon.acme.AcmeExplorer.bean.UserAccount;
import com.alvcalgon.acme.AcmeExplorer.repositories.ActorRepository;

@Service
@Transactional
public class ActorService {

	private static final Log _log = LogFactory.getLog(ActorService.class);
	
	@Autowired
	private ActorRepository actorRepository;

	@Autowired
	private UtilityService utilityService;

	public Actor findOne(int actorId) {
		Actor result = null;

		if (actorId > 0) {
			Optional<Actor> actorOpt = actorRepository.findById(actorId);

			if (actorOpt != null) {
				result = actorOpt.orElse(null);
			}
		}

		return result;
	}

	public Actor findByUsername(String username) {
		Actor result = null;

		if (StringUtils.hasText(username)) {
			result = actorRepository.findByUserName(username);
		}

		return result;
	}

	public Actor findByPrincipal() {
		Actor result = null;

		try {
			UserDetails userDetails = utilityService.getUserDetailsByPrincipal();

			if (userDetails != null) {
				String username = userDetails.getUsername();

				result = findByUsername(username);
			}
		} catch (Exception e ) {
			System.out.println("Error al recupear usuario logeado");
		}

		return result;
	}

	public boolean isASpecificRole(Actor actor, String role) {
		boolean result = Boolean.FALSE;

		if (StringUtils.hasText(role)) {
			Authority authority = new Authority(role);

			if (actor != null) {
				UserAccount userAccount = actor.getUserAccount();

				if (userAccount != null) {
					Collection<Authority> authorities = userAccount.getAuthorities();

					if (authorities != null && !authorities.isEmpty()) {
						result = authorities.contains(authority);
					}
				}
			}
		}

		return result;
	}

	public boolean checkIsUsernameUnique(String username) {
		Actor actor = findByUsername(username);
		return actor == null;
	}

	public Page<Actor> findPaginated(Pageable pageable, Collection<Actor> actors) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<Actor> list = Collections.emptyList();
		ArrayList<Actor> smaList = new ArrayList<>(actors);

		if (smaList.size() >= startItem) {
			int toIndex = Math.min(startItem + pageSize, smaList.size());
			list = smaList.subList(startItem, toIndex);
		}

		Page<Actor> smPage = new PageImpl<Actor>(list, PageRequest.of(currentPage, pageSize), smaList.size());

		return smPage;
	}

}
