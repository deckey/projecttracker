/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tapestry.projecttracker.data;

import tapestry.projecttracker.entities.Member;

/**
 *
 * @author dejan
 */
public interface MemberDAO {

    public Member getMemberById(Integer id);

    public Member getMemberByUsername(String username);

    public boolean validateMember(String uName, String pWord);
}
