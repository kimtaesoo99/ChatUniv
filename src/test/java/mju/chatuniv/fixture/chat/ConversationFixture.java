package mju.chatuniv.fixture.chat;

import mju.chatuniv.chat.domain.chat.Chat;
import mju.chatuniv.chat.domain.chat.Conversation;
import mju.chatuniv.member.domain.Member;

import static mju.chatuniv.fixture.member.MemberFixture.createMember;

public class ConversationFixture {

    public static Conversation createConversation() {
        return Conversation.from(
                "명지대학교 총장은 누구니?",
                "유병진 총장님입니다.",
                Chat.createDefault(createMember())
        );
    }

    public static Conversation createConversation(final Member member) {
        return Conversation.from(
                "명지대학교 총장은 누구니?",
                "유병진 총장님입니다.",
                Chat.createDefault(member)
        );
    }
}